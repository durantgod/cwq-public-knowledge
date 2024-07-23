# -*- coding = utf-8 -*-
# Autor : Moro

from bs4 import BeautifulSoup # 网页解析，获取数据
import re # 正则表达式，文字匹配
import urllib.request,urllib.error # 制定URL，获取网页数据
import xlwt
import sqlite3 # 进行数据库存储

def main():
    baseUrl = "https://www.pinduoduo.com/home/sale"    # 1.爬取网页
    dataList = getData(baseUrl)
    savePath = ".\\pdd数据.xls"
    dbPath = 'test.db'
    # 3.保存数据
    #saveData(savePath,dataList)
    saveData(savePath, dataList)

# 链接
findLink = re.compile(r'<a href="(.*?)">') # 创建正则表达式的对象和规则
# 图片
findImgSrc = re.compile(r'<img.*src="(.*?)"',re.S) # 忽略换行的情况
# 标题
findTitle = re.compile(r'<span class="title">(.*)</span>')
# 评分
findRating = re.compile(r'<span class="rating_num" property="v:average">(.*)</span>')

# 评价人数
findJudge = re.compile(r'<span class="group-price">(\d*)</span>')

# 概况
findInq = re.compile(r'<span class="inq">(.*)</span>')
# 相关内容
findBd = re.compile(r'<p class="">(.*?)</p>',re.S)



# 爬取网页
def getData(baseUrl):
    dataList = []
    for i in range(0,10): # 组装url
        url = baseUrl + str(i*25)
        html = askUrl(url) # 获取网页源码
        soup = BeautifulSoup(html,"html.parser")
        for item in soup.find_all('div',class_ = "item"):
            data = []
            item = str(item)

            # 解析
            link = re.findall(findLink,item)[0]
            data.append(link)

            img = re.findall(findImgSrc,item)[0]
            data.append(img)

            titles = re.findall(findTitle,item)
            if len(titles) == 2:
                ctitle = titles[0] # 中文名
                data.append(ctitle)
                otitle = titles[1].replace("/","") # 外文名
                data.append(otitle)
            else:
                data.append(titles[0])
                data.append("/")

            rating = re.findall(findRating,item)[0]
            data.append(rating)

            judge = re.findall(findJudge,item)[0]
            data.append(judge)

            ing = re.findall(findInq,item)
            if len(ing) != 0:
                ing = ing[0].replace("。","") # 去掉句号
                data.append(ing)
            else:
                data.append("/")

            bd = re.findall(findBd,item)[0]
            bd = re.sub('<br(\s+)?/>(\s+)?'," ",bd)
            bd = re.sub('/'," ",bd)
            data.append(bd.rstrip()) # 去掉前后的空格


            dataList.append(data)

    # 2.解析数据

    return dataList




# 得到指定一个网页的内容
def askUrl(url):
    head = {
        "User-Agent" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36"
    }
    req = urllib.request.Request(url,headers = head)
    try:
        resp = urllib.request.urlopen(req)
        return resp.read().decode("utf-8")
    except Exception as exc:
        print(exc.code)
        print(exc.reason)


# 初始化数据库
def init_db(dbPath):
    create_table_sql = '''
        create table if not exists movie250 (
            id integer primary key autoincrement,
            info_link text,
            pic_link text,
            cname varchar,
            ename varchar,
            score numeric,
            rated numeric,
            instroduction text,
            info text
        )
    '''
    conn = sqlite3.connect(dbPath)
    cur = conn.cursor()
    cur.execute(create_table_sql)
    conn.commit()
    cur.close()
    conn.close()

# # 保存数据到数据库
# def saveData_to_db(dbPath,dataList):
#     init_db(dbPath)
#     conn = sqlite3.connect(dbPath)
#     cur = conn.cursor()
#     for row in dataList:
#         for index in range(len(row)):
#             row[index] = '"' + row[index] +'"'
#         save_data_sql = '''
#             insert into movie250(info_link,pic_link,cname,ename,score,rated,instroduction,info)
#             values(%s)'''%",".join(row)
#         #print(save_data_sql)
#         cur.execute(save_data_sql)
#         conn.commit()
#     cur.close()
#     conn.close()



# 保存数据到excel
def saveData(savePath,content):
    print(len(content))
    try:
        book = xlwt.Workbook(encoding = "utf-8",style_compression = 0) # 创建excel对象
        sheet = book.add_sheet("豆瓣电影Top250",cell_overwrite_ok = True) # 创建Sheet表
        col = ("电影详情链接","图片链接","影片中文名","影片外文名","评分","评价数","概况","相关信息")
        for i in range(0,8):
            sheet.write(0,i,col[i]) #列名 sheet.write(行号,列号,内容)
        for i in range(0,len(content)):
            data = content[i]
            for j in range(0,8):
                sheet.write(i+1,j,data[j]) #列名
        book.save(savePath)
    except Exception as exc:
        print(exc)


if __name__ == "__main__":
    main()
