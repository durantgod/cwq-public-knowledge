# # 类似于迭代器，generator 称为生成器
# def print_range(v):
#     for val in range(v):
#         print(val)
#
# def genrate():
#     a, b = 0, 1
#     while True:
#         yield a
#         a, b = b, a + b
#
# for f1 in genrate():
#     if f1 > 100:
#         break
#     print(f1)


import pandas as pd

dic_list = [{'a': 1, 'b': 2, 'c': 3}, {'a': 4, 'b': 5, 'c': 6}]


def get_abc(l_):
    for item in l_:
        js = {'A': item['a'], 'B': item['b'], 'C': item['c']}
        yield js


print(pd.DataFrame(get_abc(dic_list)))
