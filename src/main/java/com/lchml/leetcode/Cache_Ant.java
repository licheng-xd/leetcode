package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Created by lc on 2019/03/05.
 */
public class Cache_Ant {
	public static final Logger logger = Logger.getLogger(Cache_Ant.class.getName());

	public static final long MINUTE_30 = 30 * 60 * 1000;

	private static final int lockNum = 16;

	private static final List<HashMap<String, UserInfoWrapper>> caches = new ArrayList<>();

	private static final ReentrantLock[] locks = new ReentrantLock[lockNum];

	private static AtomicBoolean init = new AtomicBoolean(false);

	public void initUserInfoCache() {
		if (init.compareAndSet(false, true)) {
			logger.info("cache init");
			for (int i = 0; i < lockNum; i++) {
				locks[i] = new ReentrantLock();
				caches.add(i, new HashMap<>());
			}
		}
	}

	public UserInfo getUserInfoFromCacheById(String userId) {
		int hash = userId.hashCode() % lockNum;
		UserInfoWrapper wrapper = caches.get(hash).get(userId);
		if (wrapper == null || wrapper.expired()) {
			logger.info("cache miss userId: " + userId);
			wrapper = lockAndRun(locks[hash], () -> caches.get(hash).get(userId));
			if (wrapper == null) {
				// load user info from somewhere
				UserInfo userInfo = new UserInfo();
				wrapper = new UserInfoWrapper(userInfo, System.currentTimeMillis());
				UserInfoWrapper finalWrapper = wrapper;
				lockAndRun(locks[hash], () -> {
					logger.info("cache update userId: " + userId);
					return caches.get(hash).put(userId, finalWrapper);
				});
			}
		} else {
			logger.info("cache hit userId: " + userId);
		}
		return wrapper.getUserInfo();
	}

	public void updateUserInfoCache(String userId) {
		int hash = userId.hashCode() % lockNum;
		// load user info from somewhere
		UserInfo userInfo = new UserInfo();
		UserInfoWrapper wrapper = new UserInfoWrapper(userInfo, System.currentTimeMillis());
		lockAndRun(locks[hash], () -> {
			logger.info("cache update userId: " + userId);
			return caches.get(hash).put(userId, wrapper);
		});
	}

	private <T> T lockAndRun(ReentrantLock lock, Callable<T> callable) {
		lock.lock();
		try {
			return callable.call();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		} finally {
			lock.unlock();
		}
	}

	public static class UserInfoWrapper {
		public UserInfoWrapper(UserInfo userInfo, long cacheTime) {
			this.userInfo = userInfo;
			this.cacheTime = cacheTime;
		}

		private UserInfo userInfo;

		private long cacheTime;

		public boolean expired() {
			return System.currentTimeMillis() - cacheTime > MINUTE_30;
		}

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		public long getCacheTime() {
			return cacheTime;
		}

		public void setCacheTime(long cacheTime) {
			this.cacheTime = cacheTime;
		}
	}


	public static class UserInfo {
		private String userId;

		private String userName;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
	}
}
