// Finds the min value in a sliding window, use push()
// to add new points to the window and poll() to
// remove points that are outside the window
public class MinMonoQueue<T extends Comparable<T>> {
  Deque<T> queue = new LinkedList<>();
  
  public void push(T obj) { // Use < for max queue
    while (!queue.isEmpty() &&
            queue.peekFirst().compareTo(obj) > 0)
      queue.pollFirst();
    queue.offerFirst(obj);
  }
  
  public T min() {
    return queue.peekLast();
  }
  
  public void pop(T obj) {
    if (queue.peekLast().compareTo(obj) == 0)
      queue.pollLast();
  }
}