package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.example.aluno.carrieon.Model.Atividade;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class AtividadeDao_Impl implements AtividadeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAtividade;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAtividade;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfAtividade;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllActivitys;

  public AtividadeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAtividade = new EntityInsertionAdapter<Atividade>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Atividade`(`codigo`,`nome`,`imagem`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Atividade value) {
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
      }
    };
    this.__deletionAdapterOfAtividade = new EntityDeletionOrUpdateAdapter<Atividade>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Atividade` WHERE `codigo` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Atividade value) {
        stmt.bindLong(1, value.getCodigo());
      }
    };
    this.__updateAdapterOfAtividade = new EntityDeletionOrUpdateAdapter<Atividade>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Atividade` SET `codigo` = ?,`nome` = ?,`imagem` = ? WHERE `codigo` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Atividade value) {
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
        stmt.bindLong(4, value.getCodigo());
      }
    };
    this.__preparedStmtOfDeleteAllActivitys = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Atividade";
        return _query;
      }
    };
  }

  @Override
  public void inserirAtividade(Atividade atividade) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAtividade.insert(atividade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void excluirAtividade(Atividade atividade) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAtividade.handle(atividade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualizarAtividade(Atividade atividade) {
    __db.beginTransaction();
    try {
      __updateAdapterOfAtividade.handle(atividade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllActivitys() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllActivitys.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllActivitys.release(_stmt);
    }
  }

  @Override
  public List<Atividade> getAllAtividades() {
    final String _sql = "SELECT * FROM Atividade";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCodigo = _cursor.getColumnIndexOrThrow("codigo");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfImg = _cursor.getColumnIndexOrThrow("imagem");
      final List<Atividade> _result = new ArrayList<Atividade>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Atividade _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpImg;
        _tmpImg = _cursor.getString(_cursorIndexOfImg);
        _item = new Atividade(_tmpNome,_tmpImg);
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
  public Atividade getAtividadeByCodigo(int codigo) {
    final String _sql = "SELECT * FROM Atividade WHERE codigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, codigo);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCodigo = _cursor.getColumnIndexOrThrow("codigo");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfImg = _cursor.getColumnIndexOrThrow("imagem");
      final Atividade _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpImg;
        _tmpImg = _cursor.getString(_cursorIndexOfImg);
        _result = new Atividade(_tmpNome,_tmpImg);
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
  public int getAtividadeByNome(String nome) {
    final String _sql = "SELECT codigo FROM Atividade WHERE nome = ?";
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
