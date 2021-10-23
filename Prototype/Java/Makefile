Name = PacMan

UNAME := $(shell uname) 

JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp"
SRC = src/fr/upec/PacMan/
BUILD = build/fr/upec/PacMan/
JC = javac 

allclass = \
	$(BUILD)test/Main.class \

default: $(allclass)

$(BUILD)test/Main.class: $(SRC)test/Main.java $(BUILD)CONTROLEUR/GameManager.class
	$(JC) $(JFLAGS) $(SRC)test/Main.java

$(BUILD)VUE/Window.class: $(SRC)VUE/Window.java $(BUILD)MODEL/Constant.class
	$(JC) $(JFLAGS) $(SRC)VUE/Window.java

$(BUILD)VUE/GameGraphics.class: $(SRC)VUE/GameGraphics.java $(BUILD)VUE/Rendering.class
	$(JC) $(JFLAGS) $(SRC)VUE/GameGraphics.java

$(BUILD)VUE/Rendering.class: $(SRC)VUE/Rendering.java
	$(JC) $(JFLAGS) $(SRC)VUE/Rendering.java

$(BUILD)VUE/InfoGraphics.class: $(SRC)VUE/InfoGraphics.java $(BUILD)MODEL/Constant.class
	$(JC) $(JFLAGS) $(SRC)VUE/InfoGraphics.java

$(BUILD)MODEL/CarteCollider.class: $(SRC)MODEL/CarteCollider.java $(SRC)MODEL/Consumable.java $(BUILD)MODEL/Vector2.class $(BUILD)MODEL/Entity.class $(BUILD)VUE/Rendering.class
	$(JC) $(JFLAGS) $(SRC)MODEL/CarteCollider.java $(SRC)MODEL/Consumable.java

$(BUILD)MODEL/Node.class: $(SRC)MODEL/Node.java $(BUILD)MODEL/Vector2.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Node.java

$(BUILD)MODEL/Actor.class: $(SRC)MODEL/Actor.java $(BUILD)MODEL/Entity.class $(BUILD)CONTROLEUR/Behaviour.class $(BUILD)VUE/Rendering.class $(BUILD)MODEL/Direction.class $(BUILD)MODEL/CarteCollider.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Actor.java

$(BUILD)MODEL/Carte.class: $(SRC)MODEL/Carte.java $(BUILD)MODEL/CarteCollider.class $(BUILD)VUE/Rendering.class $(BUILD)MODEL/Constant.class $(BUILD)MODEL/Vector2.class $(BUILD)CONTROLEUR/Behaviour.class $(BUILD)MODEL/Player.class $(BUILD)MODEL/Ghost.class $(BUILD)MODEL/Gum.class $(BUILD)VUE/InfoGraphics.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Carte.java

$(BUILD)MODEL/EffetObject.class: $(SRC)MODEL/EffetObject.java $(BUILD)MODEL/Actor.class
	$(JC) $(JFLAGS) $(SRC)MODEL/EffetObject.java

$(BUILD)MODEL/Entity.class: $(SRC)MODEL/Entity.java $(BUILD)MODEL/Vector2.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Entity.java

$(BUILD)MODEL/Player.class: $(SRC)MODEL/Player.java $(BUILD)MODEL/Actor.class $(BUILD)VUE/InfoGraphics.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Player.java

$(BUILD)MODEL/Gum.class: $(SRC)MODEL/Gum.java $(BUILD)MODEL/Consumable.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Gum.java

$(BUILD)MODEL/Ghost.class: $(SRC)MODEL/Ghost.java $(BUILD)MODEL/Actor.class $(BUILD)MODEL/Node.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Ghost.java

$(BUILD)MODEL/Constant.class: $(SRC)MODEL/Constant.java $(BUILD)MODEL/Direction.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Constant.java

$(BUILD)MODEL/Vector2.class: $(SRC)MODEL/Vector2.java
	$(JC) $(JFLAGS) $(SRC)MODEL/Vector2.java

$(BUILD)MODEL/Direction.class: $(SRC)MODEL/Direction.java $(BUILD)MODEL/Vector2.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Direction.java

$(BUILD)MODEL/Consumable.class: $(SRC)MODEL/Consumable.java $(SRC)MODEL/CarteCollider.java $(BUILD)MODEL/Entity.class $(BUILD)VUE/Rendering.class
	$(JC) $(JFLAGS) $(SRC)MODEL/Consumable.java $(SRC)MODEL/CarteCollider.java

$(BUILD)CONTROLEUR/Behaviour.class: $(SRC)CONTROLEUR/Behaviour.java
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/Behaviour.java

$(BUILD)CONTROLEUR/GameManager.class: $(SRC)CONTROLEUR/GameManager.java $(BUILD)CONTROLEUR/Behaviour.class $(BUILD)VUE/Window.class $(BUILD)VUE/GameGraphics.class $(BUILD)VUE/InfoGraphics.class $(BUILD)MODEL/Carte.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/GameManager.java


run : 
	java -cp build fr.upec.PacMan.test.Main

jar:
	jar cvfe $(Name).jar fr.upec.PacMan.test.Main -C build fr -C res Data

runjar:
	java -jar $(Name).jar

clean:
	rm -rf $(BUILD)
	rm -rf $(Name).jar

.PHONY : but clean