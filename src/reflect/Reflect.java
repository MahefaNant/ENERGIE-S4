package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import reflect.myString.MyString;

public class Reflect {

  protected Class<?> metadata;
  protected String name;
  protected Object obj;
  protected Field[] fields;

  public Reflect(Object o) {
    this.obj = o;
    build();
  }

  protected void build() {
    this.metadata = obj.getClass();
    this.name = metadata.getSimpleName();
    this.fields = metadata.getDeclaredFields();
  }

  public Object createAnInstance() {
    try {
      Constructor<?> constructor = metadata.getConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Object setter(String fieldName, Object o, Object value) throws Exception {
    Method method = metadata.getMethod("set" + MyString.toUpperIndex(fieldName, 0), value.getClass());
    Object[] args = new Object[1];
    args[0] = value;
    return method.invoke(o, args);
  }

  private Object getter(String fieldName, Object o) throws Exception {
    Method method = metadata.getMethod("get" + MyString.toUpperIndex(fieldName, 0));
    return method.invoke(o);

  }

  private Object[] getAll(Object o) {
    int taille = fields.length;
    Object[] valiny = new Object[taille];

    for (int i = 0; i < taille; i++) {
      try {
        valiny[i] = getter(fields[i].getName(), o);
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
    return valiny;
  }

  public Object getValueOf(String fieldName) {
    try {
      return getter(fieldName, this.obj);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return null;
  }

  public Object getValueOf(String fieldName, Object o) {
    try {
      return getter(fieldName, o);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return null;
  }

  public void setValueOf(String fieldName, Object arg) throws Exception {
    setter(fieldName, this.obj, arg);
  }

  public void setValueOf(String fieldName, Object o, Object arg) throws Exception {
    setter(fieldName, o, arg);
  }

  public Object[] getAllValues() {
    return getAll(this.obj);
  }

  public Object[] getAllValues(Object o) {
    return getAll(o);
  }

  public Object getObject() {
    return this.obj;
  }

  public Class<?> getMetadata() {
    return this.metadata;
  }

  public String getName() {
    return this.name;
  }

  public Field[] getAllFields() {
    return this.fields;
  }

}
