class ExceptionDemo2:
    # like the constructor
    def __init__(self):
        print("I am a constructor")

    # 捕获异常
    def __pp__(b):
        try:
            a = 10 / b
            print('结果为：', a)
            print("虽然结果正确，但是还是决定主动抛异常！")
            raise Exception("这是一个bug")
        except Exception as e:
            print(e)

    __pp__(0)
    __pp__(2)

class Student:
    def __init__(self, name, gender):
        self.name = name
        self.gender = gender

    def introduce_you_self(self):
        print("name:", self.name)
        print("gender:", self.gender)

tom = Student("张三", "male")
amy = Student("李四", "female")
tom.introduce_you_self()
amy.introduce_you_self()


ExceptionDemo2()
