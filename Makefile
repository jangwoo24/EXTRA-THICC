JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        AutoreplaceSmiles.java \
        ChatGUI.java \
        Client.java \
        ClientThread.java \
        GUI.java \
        Server.java \
        ServerThread.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
