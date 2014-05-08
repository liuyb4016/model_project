package cn.liuyb.app.portal.service;

import java.io.File;
import java.util.List;

import cn.liuyb.app.portal.domain.App;
import cn.liuyb.app.portal.domain.AppUploadInfo;

public interface AppService {
	
	
	public List<App> findAll();
	public int countAll();
	public App findByPackageName(String packageName);
	
	public App findById(Long appId);
	public App uploadApp(AppUploadInfo uploadinfo);
	public void update(App app);
	public void deleteApp(Long id);
	public App findFmById(Long appId);
	public App uploadApp(File apk);
}
