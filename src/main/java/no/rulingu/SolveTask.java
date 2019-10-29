package no.rulingu;

public class SolveTask {
    int taskNr = 0;

    public void task() {
        taskNr++;

        if (taskNr == 1) {
            task1();
        }
        else if (taskNr == 2) {
            task2();
        }
    }

    private void task1() {
        System.out.println("Hello");
    }

    private void task2() {

    }
}
