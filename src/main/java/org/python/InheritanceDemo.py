class Parent:
    def __init__(self, name):
        self.name = name
        print("parent constructor")

    def say(self):
        print(self.name, "say")

    def parent_play(self):
        print(self.name, "play")

class Mather:
    def __init__(self, name):
        self.name = name
        print("mother constructor")

    def say(self):
        print(self.name, "say")

    def mother_teach(self):
        print(self.name, "teach")

class Son(Mather, Parent):
    def __init__(self, name):
        super().__init__(name)
        self.name = name
        print("son constructor")

    def say(self):
        print(self.name, "say")

son = Son("张三")
son.say()
son.mother_teach()
son.parent_play()
print(issubclass(Son, (Parent, Mather)))
