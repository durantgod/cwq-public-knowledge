class IteratorDemo:
    def __init__(self):
        self.channel = ["HBO","cnn","abc","espn"]
        self.index = -1
    # 系统函数
    def __iter__(self):
        return self

    def __next__(self):
        self.index += 1
        if self.index == len(self.channel):
            raise StopIteration
        print(self.channel[self.index])

i = IteratorDemo()
iter = iter(i)
next(iter)
next(iter)
next(iter)
next(iter)
next(iter)
next(iter)
