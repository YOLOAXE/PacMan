Name = PacMan

UNAME := $(shell uname) 

JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp"
SRC = src/fr/upec/PacMan/
BUILD = build/fr/upec/PacMan/
JC = javac 

allclass = \
	$(BUILD)test/Main.class \

default: $(allclass)

$(BUILD)test/Main.class: $(SRC)test/Main.java $(BUILD)VUE/Window.class
	$(JC) $(JFLAGS) $(SRC)test/Main.java

$(BUILD)VUE/Window.class: $(SRC)VUE/Window.java $(BUILD)MODEL/Constant.class
	$(JC) $(JFLAGS) $(SRC)VUE/Window.java

$(BUILD)MODEL/Constant.class: $(SRC)MODEL/Constant.java
	$(JC) $(JFLAGS) $(SRC)MODEL/Constant.java

run : 
	java fr.upec.PacMan.test.Main

jar:
	jar cvfe $(Name).jar fr.upec.PacMan.test.Main -C build fr -C res Data

runjar:
	java -jar $(Name).jar

runtest :
	$(allTest)

clean:
	rm -rf $(BUILD)

.PHONY : but clean