package com.atguigu.ac.sc.factory;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StorageClientFactory implements FactoryBean<StorageClient>{
	
	@Value("${tracker.config.location}")
	private String configPath;
	
	@Override
	public StorageClient getObject() throws Exception {

//		String configPath = "/tracker.conf";
		
		String path = StorageClientFactory.class.getResource(configPath).getPath();
		
		ClientGlobal.init(path);
		
		TrackerClient trackerClient = new TrackerClient();
		
		TrackerServer trackerServer = trackerClient.getConnection();
		
		StorageServer storageServer = null;
		
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		return storageClient;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return StorageClient.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
}
