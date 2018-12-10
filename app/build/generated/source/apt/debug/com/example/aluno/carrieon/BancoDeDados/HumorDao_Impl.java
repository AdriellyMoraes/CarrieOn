package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.example.aluno.carrieon.Model.Humor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class HumorDao_Impl implements HumorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfHumor;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfHumor;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfHumor;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMoods;

  public HumorDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHumor = new EntityInsertionAdapter<Humor>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Humor`(`codigo`,`nome`,`imagem`,`valor`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Humor value) {
        stmt.bindLong(1, value.getCodigo());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getImg() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImg());
        }
        stmt.bindLong(4, value.getValor());
      }
    };
    this.__deletionAdapterOfHumor = new EntityDeletionOrUpdateAdapter<Humor>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Humor` WHERE `codigo` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Humor value) {
        stmt.bindLong(1, value.getCodigo());
      }
    };
    this.__updateAdapterOfHumor = new EntityDeletionOrUpdateAdapter<Humor>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Humor` SET `codigo` = ?,`nome` = ?,`imagem` = ?,`valor` = ? WHERE `codigo` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Humor value) {
        stmt.bindLong(1, value.getCodigo());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getImg() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImg());
        }
        stmt.bindLong(4, value.getValor());
        stmt.bindLong(5, value.getCodigo());
      }
    };
    this.__preparedStmtOfDeleteAllMoods = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Humor";
        return _query;
      }
    };
  }

  @Override
  public void inserirHumor(Humor humor) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfHumor.insert(humor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void excluirHumor(Humor humor) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfHumor.handle(humor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualizarHumor(Humor humor) {
    __db.beginTransaction();
    try {
      __updateAdapterOfHumor.handle(humor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllMoods() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMoods.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllMoods.release(_stmt);
    }
  }

  @Override
  public List<Humor> getAllHumor() {
    final String _sql = "SELECT * FROM Humor";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCodigo = _cursor.getColumnIndexOrThrow("codigo");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfImg = _cursor.getColumnIndexOrThrow("imagem");
      final int _cursorIndexOfValor = _cursor.getColumnIndexOrThrow("valor");
      final List<Humor> _result = new ArrayList<Humor>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Humor _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpImg;
        _tmpImg = _cursor.getString(_cursorIndexOfImg);
        final int _tmpValor;
        _tmpValor = _cursor.getInt(_cursorIndexOfValor);
        _item = new Humor(_tmpNome,_tmpImg,_tmpValor);
        final int _tmpCodigo;
        _tmpCodigo = _cursor.getInt(_cursorIndexOfCodigo);
        _item.setCodigo(_tmpCodigo);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Humor getHumorByCodigo(int codigo) {
    final String _sql = "SELECT * FROM Humor WHERE codigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, codigo);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCodigo = _cursor.getColumnIndexOrThrow("codigo");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfImg = _cursor.getColumnIndexOrThrow("imagem");
      final int _cursorIndexOfValor = _cursor.getColumnIndexOrThrow("valor");
      final Humor _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpImg;
        _tmpImg = _cursor.getString(_cursorIndexOfImg);
        final int _tmpValor;
        _tmpValor = _cursor.getInt(_cursorIndexOfValor);
        _result = new Humor(_tmpNome,_tmpImg,_tmpValor);
        final int _tmpCodigo;
        _tmpCodigo = _cursor.getInt(_cursorIndexOfCodigo);
        _result.setCodigo(_tmpCodigo);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getHumorByNome(String nome) {
    final String _sql = "SELECT codigo FROM Humor WHERE nome = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nome == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nome);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
