package exercises.ch3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by y.dovganich on 01.03.2017.
 */
/*
2. When you use a ReentrantLock, you are required to lock and unlock with the
idiom
myLock.lock();
try {
some action
} finally {
 myLock.unlock();
}
Provide a method withLock so that one can call
withLock(myLock, () -> { some action })
 */
public class Ex2 {
    public static void main(String[] args) {
        withLock(new ReentrantLock(), () -> System.out.println("locked action!"));
    }

    private static void withLock(ReentrantLock lock, Runnable action) {
        lock.lock();
        try {
            action.run();
        } finally {
            lock.unlock();
        }
    }
}
