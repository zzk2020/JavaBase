package cn.qiaqiatech.thread;

public class RequestTest {

    public static void main(String[] args) {
        FinishProcessor finishProcessor = new FinishProcessor();
        finishProcessor.start();
        SaveProcessor saveProcessor = new SaveProcessor(finishProcessor);
        saveProcessor.start();
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
        PrevProcessor prevProcessor = new PrevProcessor(printProcessor);
        prevProcessor.start();
        Request request = new Request("MIC");
        prevProcessor.process(request);
    }
}
