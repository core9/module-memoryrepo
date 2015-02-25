package io.core9.memoryrepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.core9.plugin.database.repository.CrudEntity;
import io.core9.plugin.database.repository.CrudRepository;
import io.core9.plugin.server.VirtualHost;

public class MemoryCrudRepository<T extends CrudEntity> implements CrudRepository<T> {
	
	private Map<VirtualHost, Map<String, T>> cache = new HashMap<VirtualHost, Map<String, T>>();

	@Override
	public T create(VirtualHost vhost, T entity) {
		if(cache.get(vhost) == null) {
			cache.put(vhost, new HashMap<String, T>());
		}
		cache.get(vhost).put(entity.getId(), entity);
		return entity;
	}

	@Override
	public T create(String database, String prefix, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T read(VirtualHost vhost, String id) {
		return read(vhost, "", id);
	}

	@Override
	public T read(VirtualHost vhost, String collection, String id) {
		if(cache.get(vhost) == null) {
			return null;
		} else {
			return cache.get(vhost).get(id);
		}
	}

	@Override
	public T read(String database, String prefix, String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T update(VirtualHost vhost, String id, T entity) {
		if(!entity.getId().equals(id)){
			entity.setId(id);
		}
		if(cache.get(vhost) == null) {
			cache.put(vhost, new HashMap<String, T>());
		} else {
			cache.get(vhost).put(entity.getId(), entity);
		}
		return entity;
	}

	@Override
	public T update(VirtualHost vhost, T entity) {
		if(cache.get(vhost) == null) {
			cache.put(vhost, new HashMap<String, T>());
		} else {
			cache.get(vhost).put(entity.getId(), entity);
		}
		return entity;
	}

	@Override
	public T update(String database, String prefix, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T update(String database, String prefix, String id, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T updateFields(VirtualHost vhost, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T updateFields(String database, String prefix, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T upsert(VirtualHost vhost, T entity) {
		if(cache.get(vhost) == null) {
			cache.put(vhost, new HashMap<String, T>());
		}
		cache.get(vhost).put(entity.getId(), entity);
		return entity;
	}

	@Override
	public T upsert(String database, String prefix, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> getAll(VirtualHost vhost) {
		if(cache.get(vhost) == null) {
			return new ArrayList<T>();
		} else {
			return new ArrayList<T>(cache.get(vhost).values());
		}
	}

	@Override
	public List<T> getAll(String database, String prefix) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> query(VirtualHost vhost, Map<String, Object> query) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> query(String database, String prefix, Map<String, Object> query) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(VirtualHost vhost, T entity) {
		if(cache.get(vhost) == null) {
			return;
		} else {
			cache.get(vhost).remove(entity.getId());
		}
	}

	@Override
	public void delete(String database, String prefix, T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(VirtualHost vhost, String id) {
		if(cache.get(vhost) == null) {
			return;
		} else {
			cache.get(vhost).remove(id);
		}
	}

	@Override
	public void delete(String database, String prefix, String id) {
		throw new UnsupportedOperationException();
	}

}
