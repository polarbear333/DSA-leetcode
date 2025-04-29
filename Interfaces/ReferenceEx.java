import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.ref.Reference;


class myObject{
    private String name;

    public myObject(String name){
        this.name = name;
        System.out.println("MyObject '" + name + "' created.");
    }

    @Override
    protected void finalize() throws Throwable{
        System.out.println("MyObject '" + name + "' finalized (garbage collected).");
        //super.finalize();
    }

    public String getName(){
        return name;
    }
}



public class ReferenceEx {
    public static void main(String[] args) throws InterruptedException {
        myObject obj = new myObject("Weakly Referenced Object with Queue");
        ReferenceQueue<myObject> queue = new ReferenceQueue<>();
        WeakReference<myObject> weakRef = new WeakReference<>(obj, queue);

        System.out.println("Weak reference created: " + weakRef);
        System.out.println("Original object: " + obj);

        obj = null; // Remove strong reference
        System.out.println("Strong reference 'obj' set to null.");

        System.out.println("Trying to get from weak ref before GC: " + weakRef.get());

        System.out.println("Suggesting garbage collection...");
        System.gc();
        Thread.sleep(3000); // Give GC time to run
        
        System.out.println("Trying to get from weak ref after GC: " + weakRef.get());

        // Check the reference queue
        Reference<?> polledReference = queue.poll();
        if (polledReference != null) {
            System.out.println("Reference enqueued in the queue: " + polledReference);
            // At this point, you know the MyObject has been garbage collected,
            // and you can perform cleanup based on the 'weakRef' object itself.
        } else {
            System.out.println("No reference enqueued in the queue yet.");
        }
    }
    
}
