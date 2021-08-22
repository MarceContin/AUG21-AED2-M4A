package uy.edu.ort.ad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public
class Executor
{

    private static Executor executor;
    private
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private
    Executor()
    {

    }

    public static synchronized
    Executor getExecutor()
    {
        if (executor == null)
        {
            executor = new Executor();
        }
        return executor;
    }

    public
    void submit(Runnable t)
    {

        try
        {

            this.executorService.submit(t);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public
    void exit()
    {
        this.executorService.shutdown();
    }
}
