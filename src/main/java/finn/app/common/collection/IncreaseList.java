package finn.app.common.collection;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Finn Zhao
 * @version 2019-09-28
 */
public class IncreaseList<E> extends ForwardingList<E> {

    public IncreaseList(Class<E> entryClass) {
        super();
        this.entryClass = entryClass;
        this.delegate = Lists.newArrayList();
    }

    private final Class<E> entryClass;

    private List<E> delegate;

    @Override
    protected List<E> delegate() {
        return this.delegate;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size()) {
            increaseEntry(index);
        }
        return delegate().set(index, element);
    }

    @Override
    public void add(int index, E element) {
        if (index >= size()) {
            increaseEntry(index);
        }
        delegate().add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        if (elements == null) {
            throw new NullPointerException("Collection to add is null");
        } else {
            boolean trim = false;
            if (index >= this.size()) {
                trim = true;
            }
            for (Iterator<E> it = (Iterator<E>) elements.iterator(); it.hasNext(); ++index) {
                this.add(index, it.next());
            }
            if (trim) {
                this.remove(this.size() - 1);
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= size()) {
            increaseEntry(index);
        }
        return delegate().get(index);
    }

    private void increaseEntry(int index) {
        try {
            synchronized (this) {
                while (index >= size()) {
                    add(entryClass.newInstance());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
