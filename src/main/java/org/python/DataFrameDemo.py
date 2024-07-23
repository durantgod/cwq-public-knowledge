import pandas as pd
import InheritanceDemo as do

# dic_list = [{'a': 1, 'b': 2, 'c': 3}, {'a': 4, 'b': 5, 'c': 6}]
#
#
# def get_abc(l_):
#     for item in l_:
#         js = {'A': item['a'], 'B': item['b'], 'C': item['c']}
#         yield js
#
#
# ls = pd.DataFrame(get_abc(dic_list))
# print(ls[1:1])
son = do.Son("李四")
son.say()
