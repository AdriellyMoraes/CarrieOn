package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.example.aluno.carrieon.Model.Pessoa;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PessoaDao_Impl implements PessoaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPessoa;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPessoa;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPessoa;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUsers;

  public PessoaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPessoa = new EntityInsertionAdapter<Pessoa>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Pessoa`(`email`,`nome`,`dataNasc`,`senha`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Pessoa value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getDataNasc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDataNasc());
        }
        if (value.getSenha() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSenha());
        }
      }
    };
    this.__deletionAdapterOfPessoa = new EntityDeletionOrUpdateAdapter<Pessoa>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Pessoa` WHERE `email` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Pessoa value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
      }
    };
    this.__updateAdapterOfPessoa = new EntityDeletionOrUpdateAdapter<Pessoa>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Pessoa` SET `email` = ?,`nome` = ?,`dataNasc` = ?,`senha` = ? WHERE `email` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Pessoa value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getDataNasc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDataNasc());
        }
        if (value.getSenha() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSenha());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
      }
    };
    this.__preparedStmtOfDeleteAllUsers = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Pessoa";
        return _query;
      }
    };
  }

  @Override
  public void inserirPessoa(Pessoa pessoa) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPessoa.insert(pessoa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void excluirPessoa(Pessoa pessoa) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPessoa.handle(pessoa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualizarPessoa(Pessoa pessoa) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPessoa.handle(pessoa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllUsers() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUsers.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllUsers.release(_stmt);
    }
  }

  @Override
  public List<Pessoa> getAllPessoas() {
    final String _sql = "SELECT * FROM Pessoa";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfDataNasc = _cursor.getColumnIndexOrThrow("dataNasc");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final List<Pessoa> _result = new ArrayList<Pessoa>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Pessoa _item;
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDataNasc;
        _tmpDataNasc = _cursor.getString(_cursorIndexOfDataNasc);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _item = new Pessoa(_tmpEmail,_tmpNome,_tmpDataNasc,_tmpSenha);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Pessoa pessoaLogada() {
    final String _sql = "SELECT * FROM Pessoa ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfDataNasc = _cursor.getColumnIndexOrThrow("dataNasc");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final Pessoa _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDataNasc;
        _tmpDataNasc = _cursor.getString(_cursorIndexOfDataNasc);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _result = new Pessoa(_tmpEmail,_tmpNome,_tmpDataNasc,_tmpSenha);
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
  public Pessoa getUserByEmail(String email) {
    final String _sql = "SELECT * FROM Pessoa WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfDataNasc = _cursor.getColumnIndexOrThrow("dataNasc");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final Pessoa _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDataNasc;
        _tmpDataNasc = _cursor.getString(_cursorIndexOfDataNasc);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _result = new Pessoa(_tmpEmail,_tmpNome,_tmpDataNasc,_tmpSenha);
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
  public Pessoa login(String email, String senha) {
    final String _sql = "SELECT * FROM Pessoa WHERE email = ? AND senha = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    _argIndex = 2;
    if (senha == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, senha);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfDataNasc = _cursor.getColumnIndexOrThrow("dataNasc");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final Pessoa _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDataNasc;
        _tmpDataNasc = _cursor.getString(_cursorIndexOfDataNasc);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _result = new Pessoa(_tmpEmail,_tmpNome,_tmpDataNasc,_tmpSenha);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
