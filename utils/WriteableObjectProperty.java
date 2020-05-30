package elaborato_ing_sw.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleObjectProperty;

public class WriteableObjectProperty<T> extends SimpleObjectProperty<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public WriteableObjectProperty() {
		super();
	}

	public WriteableObjectProperty(T obj) {
		super(obj);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeObject(get());
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		set((T) s.readObject());
	}
}