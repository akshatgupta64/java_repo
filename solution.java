interface ModerationRule {
    int addExtraMarks(int marks);
}

class SimpleModeration implements ModerationRule {
    public int addExtraMarks(int marks) {
        return marks + 5;
    }
}

abstract class Evaluation {

    ModerationRule rule;

    Evaluation(ModerationRule rule) {
        this.rule = rule;
    }

    void evaluate() {
        int theoryMarks = 70;
        int labMarks = 30;

        int totalMarks = calculateMarks(theoryMarks, labMarks);
        totalMarks = rule.addExtraMarks(totalMarks);
        checkResult(totalMarks);
    }

    abstract int calculateMarks(int theory, int lab);
    abstract void checkResult(int total);
}

class BTech extends Evaluation {

    BTech(ModerationRule rule) {
        super(rule);
    }

    int calculateMarks(int theory, int lab) {
        return theory + lab;
    }

    void checkResult(int total) {
        if (total >= 50)
            System.out.println("BTech: PASS");
        else
            System.out.println("BTech: FAIL");
    }
}

class MCA extends Evaluation {

    MCA(ModerationRule rule) {
        super(rule);
    }

    int calculateMarks(int theory, int lab) {
        return (theory + lab) / 2;
    }

    void checkResult(int total) {
        if (total >= 60)
            System.out.println("MCA: PASS");
        else
            System.out.println("MCA: FAIL");
    }
}

public class Experiment {
    public static void main(String[] args) {

        Evaluation btechStudent = new BTech(new SimpleModeration());
        btechStudent.evaluate();

        Evaluation mcaStudent = new MCA(new SimpleModeration());
        mcaStudent.evaluate();
    }
}