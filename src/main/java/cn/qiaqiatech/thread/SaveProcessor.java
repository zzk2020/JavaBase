package cn.qiaqiatech.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements IRequestProcessor {
    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    private boolean isFinish = false;

    private IRequestProcessor nextProcessor;

    public SaveProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }
    public void shutdown() {
        isFinish = true;
    }


    @Override
    public void run() {
        while (!isFinish) {
            // TODO
            try {
                Request request = queue.take();
                System.out.println("SaveProcessor " + request);
                nextProcessor.process(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void process(Request request) {
        queue.add(request);
    }
}
