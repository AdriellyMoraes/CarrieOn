package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.example.aluno.carrieon.Model.Notacao;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class NotacaoDao_Impl implements NotacaoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNotacao;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNotacao;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfNotacao;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllNotes;

  public NotacaoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotacao = new EntityInsertionAdapter<Notacao>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Notacao`(`numero`,`descricao`,`dataHoraCriacao`,`titulo`,`salva`,`emailPessoa`,`codAtividade`,`codHumor`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notacao value) {
        stmt.bindLong(1, value.getNumero());
        if (value.getDescricao() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescricao());
        }
        if (value.getDataHoraCriacao() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDataHoraCriacao());
        }
        if (value.getTitulo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitulo());
        }
        final int _tmp;
        _tmp = value.isSalva() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getEmailPessoa() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmailPessoa());
        }
        stmt.bindLong(7, value.getCodAtividade());
        stmt.bindLong(8, value.getCodHumor());
      }
    };
    this.__deletionAdapterOfNotacao = new EntityDeletionOrUpdateAdapter<Notacao>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Notacao` WHERE `numero` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notacao value) {
        stmt.bindLong(1, value.getNumero());
      }
    };
    this.__updateAdapterOfNotacao = new EntityDeletionOrUpdateAdapter<Notacao>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Notacao` SET `numero` = ?,`descricao` = ?,`dataHoraCriacao` = ?,`titulo` = ?,`salva` = ?,`emailPessoa` = ?,`codAtividade` = ?,`codHumor` = ? WHERE `numero` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notacao value) {
        stmt.bindLong(1, value.getNumero());
        if (value.getDescricao() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescricao());
        }
        if (value.getDataHoraCriacao() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDataHoraCriacao());
        }
        if (value.getTitulo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitulo());
        }
        final int _tmp;
        _tmp = value.isSalva() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getEmailPessoa() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmailPessoa());
        }
        stmt.bindLong(7, value.getCodAtividade());
        stmt.bindLong(8, value.getCodHumor());
        stmt.bindLong(9, value.getNumero());
      }
    };
    this.__preparedStmtOfDeleteAllNotes = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Notacao";
        return _query;
      }
    };
  }

  @Override
  public void inserirNotacao(Notacao notacao) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotacao.insert(notacao);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void excluirNotacao(Notacao notacao) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNotacao.handle(notacao);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualizarNotacao(Notacao notacao) {
    __db.beginTransaction();
    try {
      __updateAdapterOfNotacao.handle(notacao);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllNotes() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllNotes.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllNotes.release(_stmt);
    }
  }

  @Override
  public List<Notacao> getAllNotacao() {
    final String _sql = "SELECT * FROM Notacao";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfNumero = _cursor.getColumnIndexOrThrow("numero");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDataHoraCriacao = _cursor.getColumnIndexOrThrow("dataHoraCriacao");
      final int _cursorIndexOfTitulo = _cursor.getColumnIndexOrThrow("titulo");
      final int _cursorIndexOfSalva = _cursor.getColumnIndexOrThrow("salva");
      final int _cursorIndexOfEmailPessoa = _cursor.getColumnIndexOrThrow("emailPessoa");
      final int _cursorIndexOfCodAtividade = _cursor.getColumnIndexOrThrow("codAtividade");
      final int _cursorIndexOfCodHumor = _cursor.getColumnIndexOrThrow("codHumor");
      final List<Notacao> _result = new ArrayList<Notacao>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Notacao _item;
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final String _tmpDataHoraCriacao;
        _tmpDataHoraCriacao = _cursor.getString(_cursorIndexOfDataHoraCriacao);
        final String _tmpTitulo;
        _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        final boolean _tmpSalva;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSalva);
        _tmpSalva = _tmp != 0;
        final String _tmpEmailPessoa;
        _tmpEmailPessoa = _cursor.getString(_cursorIndexOfEmailPessoa);
        final int _tmpCodAtividade;
        _tmpCodAtividade = _cursor.getInt(_cursorIndexOfCodAtividade);
        final int _tmpCodHumor;
        _tmpCodHumor = _cursor.getInt(_cursorIndexOfCodHumor);
        _item = new Notacao(_tmpDescricao,_tmpDataHoraCriacao,_tmpTitulo,_tmpSalva,_tmpEmailPessoa,_tmpCodAtividade,_tmpCodHumor);
        final int _tmpNumero;
        _tmpNumero = _cursor.getInt(_cursorIndexOfNumero);
        _item.setNumero(_tmpNumero);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Notacao getNotacaoByNumero(int numero) {
    final String _sql = "SELECT * FROM Notacao WHERE numero = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, numero);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfNumero = _cursor.getColumnIndexOrThrow("numero");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDataHoraCriacao = _cursor.getColumnIndexOrThrow("dataHoraCriacao");
      final int _cursorIndexOfTitulo = _cursor.getColumnIndexOrThrow("titulo");
      final int _cursorIndexOfSalva = _cursor.getColumnIndexOrThrow("salva");
      final int _cursorIndexOfEmailPessoa = _cursor.getColumnIndexOrThrow("emailPessoa");
      final int _cursorIndexOfCodAtividade = _cursor.getColumnIndexOrThrow("codAtividade");
      final int _cursorIndexOfCodHumor = _cursor.getColumnIndexOrThrow("codHumor");
      final Notacao _result;
      if(_cursor.moveToFirst()) {
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final String _tmpDataHoraCriacao;
        _tmpDataHoraCriacao = _cursor.getString(_cursorIndexOfDataHoraCriacao);
        final String _tmpTitulo;
        _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        final boolean _tmpSalva;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSalva);
        _tmpSalva = _tmp != 0;
        final String _tmpEmailPessoa;
        _tmpEmailPessoa = _cursor.getString(_cursorIndexOfEmailPessoa);
        final int _tmpCodAtividade;
        _tmpCodAtividade = _cursor.getInt(_cursorIndexOfCodAtividade);
        final int _tmpCodHumor;
        _tmpCodHumor = _cursor.getInt(_cursorIndexOfCodHumor);
        _result = new Notacao(_tmpDescricao,_tmpDataHoraCriacao,_tmpTitulo,_tmpSalva,_tmpEmailPessoa,_tmpCodAtividade,_tmpCodHumor);
        final int _tmpNumero;
        _tmpNumero = _cursor.getInt(_cursorIndexOfNumero);
        _result.setNumero(_tmpNumero);
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
  public Notacao getNotacaoByHumor(int codHumor) {
    final String _sql = "SELECT * FROM Notacao WHERE codHumor = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, codHumor);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfNumero = _cursor.getColumnIndexOrThrow("numero");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDataHoraCriacao = _cursor.getColumnIndexOrThrow("dataHoraCriacao");
      final int _cursorIndexOfTitulo = _cursor.getColumnIndexOrThrow("titulo");
      final int _cursorIndexOfSalva = _cursor.getColumnIndexOrThrow("salva");
      final int _cursorIndexOfEmailPessoa = _cursor.getColumnIndexOrThrow("emailPessoa");
      final int _cursorIndexOfCodAtividade = _cursor.getColumnIndexOrThrow("codAtividade");
      final int _cursorIndexOfCodHumor = _cursor.getColumnIndexOrThrow("codHumor");
      final Notacao _result;
      if(_cursor.moveToFirst()) {
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final String _tmpDataHoraCriacao;
        _tmpDataHoraCriacao = _cursor.getString(_cursorIndexOfDataHoraCriacao);
        final String _tmpTitulo;
        _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        final boolean _tmpSalva;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSalva);
        _tmpSalva = _tmp != 0;
        final String _tmpEmailPessoa;
        _tmpEmailPessoa = _cursor.getString(_cursorIndexOfEmailPessoa);
        final int _tmpCodAtividade;
        _tmpCodAtividade = _cursor.getInt(_cursorIndexOfCodAtividade);
        final int _tmpCodHumor;
        _tmpCodHumor = _cursor.getInt(_cursorIndexOfCodHumor);
        _result = new Notacao(_tmpDescricao,_tmpDataHoraCriacao,_tmpTitulo,_tmpSalva,_tmpEmailPessoa,_tmpCodAtividade,_tmpCodHumor);
        final int _tmpNumero;
        _tmpNumero = _cursor.getInt(_cursorIndexOfNumero);
        _result.setNumero(_tmpNumero);
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
  public List<Notacao> getNotacaoByEmail(String emailPessoa) {
    final String _sql = "SELECT * FROM Notacao WHERE emailPessoa = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (emailPessoa == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, emailPessoa);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfNumero = _cursor.getColumnIndexOrThrow("numero");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDataHoraCriacao = _cursor.getColumnIndexOrThrow("dataHoraCriacao");
      final int _cursorIndexOfTitulo = _cursor.getColumnIndexOrThrow("titulo");
      final int _cursorIndexOfSalva = _cursor.getColumnIndexOrThrow("salva");
      final int _cursorIndexOfEmailPessoa = _cursor.getColumnIndexOrThrow("emailPessoa");
      final int _cursorIndexOfCodAtividade = _cursor.getColumnIndexOrThrow("codAtividade");
      final int _cursorIndexOfCodHumor = _cursor.getColumnIndexOrThrow("codHumor");
      final List<Notacao> _result = new ArrayList<Notacao>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Notacao _item;
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final String _tmpDataHoraCriacao;
        _tmpDataHoraCriacao = _cursor.getString(_cursorIndexOfDataHoraCriacao);
        final String _tmpTitulo;
        _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        final boolean _tmpSalva;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSalva);
        _tmpSalva = _tmp != 0;
        final String _tmpEmailPessoa;
        _tmpEmailPessoa = _cursor.getString(_cursorIndexOfEmailPessoa);
        final int _tmpCodAtividade;
        _tmpCodAtividade = _cursor.getInt(_cursorIndexOfCodAtividade);
        final int _tmpCodHumor;
        _tmpCodHumor = _cursor.getInt(_cursorIndexOfCodHumor);
        _item = new Notacao(_tmpDescricao,_tmpDataHoraCriacao,_tmpTitulo,_tmpSalva,_tmpEmailPessoa,_tmpCodAtividade,_tmpCodHumor);
        final int _tmpNumero;
        _tmpNumero = _cursor.getInt(_cursorIndexOfNumero);
        _item.setNumero(_tmpNumero);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Notacao> getNotacaoByDate(String data) {
    final String _sql = "SELECT * FROM Notacao WHERE dataHoraCriacao LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (data == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, data);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfNumero = _cursor.getColumnIndexOrThrow("numero");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDataHoraCriacao = _cursor.getColumnIndexOrThrow("dataHoraCriacao");
      final int _cursorIndexOfTitulo = _cursor.getColumnIndexOrThrow("titulo");
      final int _cursorIndexOfSalva = _cursor.getColumnIndexOrThrow("salva");
      final int _cursorIndexOfEmailPessoa = _cursor.getColumnIndexOrThrow("emailPessoa");
      final int _cursorIndexOfCodAtividade = _cursor.getColumnIndexOrThrow("codAtividade");
      final int _cursorIndexOfCodHumor = _cursor.getColumnIndexOrThrow("codHumor");
      final List<Notacao> _result = new ArrayList<Notacao>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Notacao _item;
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final String _tmpDataHoraCriacao;
        _tmpDataHoraCriacao = _cursor.getString(_cursorIndexOfDataHoraCriacao);
        final String _tmpTitulo;
        _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        final boolean _tmpSalva;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSalva);
        _tmpSalva = _tmp != 0;
        final String _tmpEmailPessoa;
        _tmpEmailPessoa = _cursor.getString(_cursorIndexOfEmailPessoa);
        final int _tmpCodAtividade;
        _tmpCodAtividade = _cursor.getInt(_cursorIndexOfCodAtividade);
        final int _tmpCodHumor;
        _tmpCodHumor = _cursor.getInt(_cursorIndexOfCodHumor);
        _item = new Notacao(_tmpDescricao,_tmpDataHoraCriacao,_tmpTitulo,_tmpSalva,_tmpEmailPessoa,_tmpCodAtividade,_tmpCodHumor);
        final int _tmpNumero;
        _tmpNumero = _cursor.getInt(_cursorIndexOfNumero);
        _item.setNumero(_tmpNumero);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
