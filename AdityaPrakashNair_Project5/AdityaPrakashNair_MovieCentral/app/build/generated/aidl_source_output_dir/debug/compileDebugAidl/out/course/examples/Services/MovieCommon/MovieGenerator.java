/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package course.examples.Services.MovieCommon;
public interface MovieGenerator extends android.os.IInterface
{
  /** Default implementation for MovieGenerator. */
  public static class Default implements course.examples.Services.MovieCommon.MovieGenerator
  {
    @Override public java.lang.String[] getAllMoviesTitles() throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.lang.String[] getAllMoviesDirectors() throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.lang.String[] getAllMoviesURL() throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.lang.String[] getMovieById(int id) throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.lang.String getMovieUrl(int id) throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements course.examples.Services.MovieCommon.MovieGenerator
  {
    private static final java.lang.String DESCRIPTOR = "course.examples.Services.MovieCommon.MovieGenerator";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an course.examples.Services.MovieCommon.MovieGenerator interface,
     * generating a proxy if needed.
     */
    public static course.examples.Services.MovieCommon.MovieGenerator asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof course.examples.Services.MovieCommon.MovieGenerator))) {
        return ((course.examples.Services.MovieCommon.MovieGenerator)iin);
      }
      return new course.examples.Services.MovieCommon.MovieGenerator.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_getAllMoviesTitles:
        {
          data.enforceInterface(descriptor);
          java.lang.String[] _result = this.getAllMoviesTitles();
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_getAllMoviesDirectors:
        {
          data.enforceInterface(descriptor);
          java.lang.String[] _result = this.getAllMoviesDirectors();
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_getAllMoviesURL:
        {
          data.enforceInterface(descriptor);
          java.lang.String[] _result = this.getAllMoviesURL();
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_getMovieById:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String[] _result = this.getMovieById(_arg0);
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_getMovieUrl:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _result = this.getMovieUrl(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements course.examples.Services.MovieCommon.MovieGenerator
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public java.lang.String[] getAllMoviesTitles() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getAllMoviesTitles, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getAllMoviesTitles();
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String[] getAllMoviesDirectors() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getAllMoviesDirectors, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getAllMoviesDirectors();
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String[] getAllMoviesURL() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getAllMoviesURL, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getAllMoviesURL();
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String[] getMovieById(int id) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(id);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getMovieById, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getMovieById(id);
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String getMovieUrl(int id) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(id);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getMovieUrl, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getMovieUrl(id);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static course.examples.Services.MovieCommon.MovieGenerator sDefaultImpl;
    }
    static final int TRANSACTION_getAllMoviesTitles = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getAllMoviesDirectors = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getAllMoviesURL = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_getMovieById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_getMovieUrl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    public static boolean setDefaultImpl(course.examples.Services.MovieCommon.MovieGenerator impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static course.examples.Services.MovieCommon.MovieGenerator getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public java.lang.String[] getAllMoviesTitles() throws android.os.RemoteException;
  public java.lang.String[] getAllMoviesDirectors() throws android.os.RemoteException;
  public java.lang.String[] getAllMoviesURL() throws android.os.RemoteException;
  public java.lang.String[] getMovieById(int id) throws android.os.RemoteException;
  public java.lang.String getMovieUrl(int id) throws android.os.RemoteException;
}
