package io.core9.memoryrepo;

import java.util.HashMap;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import io.core9.plugin.database.repository.CrudEntity;
import io.core9.plugin.database.repository.CrudRepository;
import io.core9.plugin.database.repository.NoCollectionNamePresentException;
import io.core9.plugin.database.repository.ObservableCrudRepository;
import io.core9.plugin.database.repository.RepositoryFactory;

@PluginImplementation
public class MemoryRepositoryFactoryImpl implements RepositoryFactory {
	
	Map<Class<?>, CrudRepository<?>> repos = new HashMap<Class<?>, CrudRepository<? extends CrudEntity>>();

	@Override
	public <T extends CrudEntity> CrudRepository<T> getRepository(Class<T> type) throws NoCollectionNamePresentException {
		return getRepository(type, false);
	}

	@Override
	public <T extends CrudEntity> CrudRepository<T> getRepository(Class<T> type, boolean dynamicCollectionName)	throws NoCollectionNamePresentException {
		@SuppressWarnings("unchecked")
		CrudRepository<T> repo = (CrudRepository<T>) repos.get(type); 
		if(repos.get(type) == null) {
			return createRepository(type);
		}
		return repo;
	}

	@Override
	public <T extends CrudEntity> CrudRepository<T> getCachedRepository(Class<T> type) throws NoCollectionNamePresentException {
		return getRepository(type, false);
	}

	@Override
	public <T extends CrudEntity> ObservableCrudRepository<T> getObservableRepository(Class<T> type) throws NoCollectionNamePresentException {
		throw new UnsupportedOperationException("No ObservableRepository avaiable for the in memory repo");
	}

	private <T extends CrudEntity> CrudRepository<T> createRepository(Class<T> type) {
		CrudRepository<T> repo = new MemoryCrudRepository<T>();
		repos.put(type, repo);
		return repo;
	}
}
