import json

f = open("d://wq.txt", 'w')

dictionary = {}
command = ""

while command != 'exit':
    command = input("enter you command like 'exit','write','save':")
    if command == "write":
        name = input("input you name:")
        sex = input("input you sex:")
        dictionary[name] = sex
    elif command == "save":
        # 注意，只有当程序结束才会实际写入
        f.write(json.dumps(dictionary))
        print("保存成功！")
    elif command == "print":
        # 注意前面如果有open read 会清空文件内容
        with open("d://wq.txt", 'r') as rr:
            for line in rr.readline():
                print(line)

