package com.example.aluno.carrieon.BancoDeDados;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class BancoDados_Impl extends BancoDados {
  private volatile PessoaDao _pessoaDao;

  private volatile AtividadeDao _atividadeDao;

  private volatile HumorDao _humorDao;

  private volatile NotacaoDao _notacaoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(10) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Pessoa` (`email` TEXT NOT NULL, `nome` TEXT NOT NULL, `dataNasc` TEXT NOT NULL, `senha` TEXT NOT NULL, PRIMARY KEY(`email`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Atividade` (`codigo` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `imagem` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Humor` (`codigo` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `imagem` TEXT NOT NULL, `valor` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Notacao` (`numero` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `descricao` TEXT NOT NULL, `dataHoraCriacao` TEXT NOT NULL, `titulo` TEXT NOT NULL, `salva` INTEGER NOT NULL, `emailPessoa` TEXT NOT NULL, `codAtividade` INTEGER NOT NULL, `codHumor` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7bc015ba28e9a746dbdbf0c52c30bb5d\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Pessoa`");
        _db.execSQL("DROP TABLE IF EXISTS `Atividade`");
        _db.execSQL("DROP TABLE IF EXISTS `Humor`");
        _db.execSQL("DROP TABLE IF EXISTS `Notacao`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPessoa = new HashMap<String, TableInfo.Column>(4);
        _columnsPessoa.put("email", new TableInfo.Column("email", "TEXT", true, 1));
        _columnsPessoa.put("nome", new TableInfo.Column("nome", "TEXT", true, 0));
        _columnsPessoa.put("dataNasc", new TableInfo.Column("dataNasc", "TEXT", true, 0));
        _columnsPessoa.put("senha", new TableInfo.Column("senha", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPessoa = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPessoa = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPessoa = new TableInfo("Pessoa", _columnsPessoa, _foreignKeysPessoa, _indicesPessoa);
        final TableInfo _existingPessoa = TableInfo.read(_db, "Pessoa");
        if (! _infoPessoa.equals(_existingPessoa)) {
          throw new IllegalStateException("Migration didn't properly handle Pessoa(com.example.aluno.carrieon.Model.Pessoa).\n"
                  + " Expected:\n" + _infoPessoa + "\n"
                  + " Found:\n" + _existingPessoa);
        }
        final HashMap<String, TableInfo.Column> _columnsAtividade = new HashMap<String, TableInfo.Column>(3);
        _columnsAtividade.put("codigo", new TableInfo.Column("codigo", "INTEGER", true, 1));
        _columnsAtividade.put("nome", new TableInfo.Column("nome", "TEXT", true, 0));
        _columnsAtividade.put("imagem", new TableInfo.Column("imagem", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAtividade = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAtividade = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAtividade = new TableInfo("Atividade", _columnsAtividade, _foreignKeysAtividade, _indicesAtividade);
        final TableInfo _existingAtividade = TableInfo.read(_db, "Atividade");
        if (! _infoAtividade.equals(_existingAtividade)) {
          throw new IllegalStateException("Migration didn't properly handle Atividade(com.example.aluno.carrieon.Model.Atividade).\n"
                  + " Expected:\n" + _infoAtividade + "\n"
                  + " Found:\n" + _existingAtividade);
        }
        final HashMap<String, TableInfo.Column> _columnsHumor = new HashMap<String, TableInfo.Column>(4);
        _columnsHumor.put("codigo", new TableInfo.Column("codigo", "INTEGER", true, 1));
        _columnsHumor.put("nome", new TableInfo.Column("nome", "TEXT", true, 0));
        _columnsHumor.put("imagem", new TableInfo.Column("imagem", "TEXT", true, 0));
        _columnsHumor.put("valor", new TableInfo.Column("valor", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHumor = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHumor = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHumor = new TableInfo("Humor", _columnsHumor, _foreignKeysHumor, _indicesHumor);
        final TableInfo _existingHumor = TableInfo.read(_db, "Humor");
        if (! _infoHumor.equals(_existingHumor)) {
          throw new IllegalStateException("Migration didn't properly handle Humor(com.example.aluno.carrieon.Model.Humor).\n"
                  + " Expected:\n" + _infoHumor + "\n"
                  + " Found:\n" + _existingHumor);
        }
        final HashMap<String, TableInfo.Column> _columnsNotacao = new HashMap<String, TableInfo.Column>(8);
        _columnsNotacao.put("numero", new TableInfo.Column("numero", "INTEGER", true, 1));
        _columnsNotacao.put("descricao", new TableInfo.Column("descricao", "TEXT", true, 0));
        _columnsNotacao.put("dataHoraCriacao", new TableInfo.Column("dataHoraCriacao", "TEXT", true, 0));
        _columnsNotacao.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0));
        _columnsNotacao.put("salva", new TableInfo.Column("salva", "INTEGER", true, 0));
        _columnsNotacao.put("emailPessoa", new TableInfo.Column("emailPessoa", "TEXT", true, 0));
        _columnsNotacao.put("codAtividade", new TableInfo.Column("codAtividade", "INTEGER", true, 0));
        _columnsNotacao.put("codHumor", new TableInfo.Column("codHumor", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotacao = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotacao = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotacao = new TableInfo("Notacao", _columnsNotacao, _foreignKeysNotacao, _indicesNotacao);
        final TableInfo _existingNotacao = TableInfo.read(_db, "Notacao");
        if (! _infoNotacao.equals(_existingNotacao)) {
          throw new IllegalStateException("Migration didn't properly handle Notacao(com.example.aluno.carrieon.Model.Notacao).\n"
                  + " Expected:\n" + _infoNotacao + "\n"
                  + " Found:\n" + _existingNotacao);
        }
      }
    }, "7bc015ba28e9a746dbdbf0c52c30bb5d", "38234fcbeb5858d0c711ef3c88ca6389");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Pessoa","Atividade","Humor","Notacao");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Pessoa`");
      _db.execSQL("DELETE FROM `Atividade`");
      _db.execSQL("DELETE FROM `Humor`");
      _db.execSQL("DELETE FROM `Notacao`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PessoaDao pessoaDao() {
    if (_pessoaDao != null) {
      return _pessoaDao;
    } else {
      synchronized(this) {
        if(_pessoaDao == null) {
          _pessoaDao = new PessoaDao_Impl(this);
        }
        return _pessoaDao;
      }
    }
  }

  @Override
  public AtividadeDao atividadeDao() {
    if (_atividadeDao != null) {
      return _atividadeDao;
    } else {
      synchronized(this) {
        if(_atividadeDao == null) {
          _atividadeDao = new AtividadeDao_Impl(this);
        }
        return _atividadeDao;
      }
    }
  }

  @Override
  public HumorDao humorDao() {
    if (_humorDao != null) {
      return _humorDao;
    } else {
      synchronized(this) {
        if(_humorDao == null) {
          _humorDao = new HumorDao_Impl(this);
        }
        return _humorDao;
      }
    }
  }

  @Override
  public NotacaoDao notacaoDao() {
    if (_notacaoDao != null) {
      return _notacaoDao;
    } else {
      synchronized(this) {
        if(_notacaoDao == null) {
          _notacaoDao = new NotacaoDao_Impl(this);
        }
        return _notacaoDao;
      }
    }
  }
}
