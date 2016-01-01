package com.frame.web;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.frame.bean.GridBean;

public class SessionManager {
	Hashtable<String,HttpSession> logginedSessions = new Hashtable<String,HttpSession>();
	List<ApplicationModel> userList = new ArrayList<ApplicationModel>();
	
	private static SessionManager instance = new SessionManager();
	
	
	private SessionManager(){
		
	}
	
	public static SessionManager getInstance(){
		return instance;
	}
	public void addSession(String id,HttpSession session){
		//if(session.getAttribute("appModel") != null){
		logginedSessions.put(id, session);
		//}
	}
	
	public void removeSession(String id){
		logginedSessions.remove(id);
	}
	
	public int getOnlineUserCount(){
		resetUserList();
		return userList.size();
	}
	
	public List<ApplicationModel> getOnlineUserList(){
		resetUserList();
		return userList;
	}
	
	private void resetUserList(){
		Iterator<String> iter = logginedSessions.keySet().iterator();
		String id;
		userList.clear();
		HttpSession ss;
		while(iter.hasNext()){
			id = iter.next();
			ss = logginedSessions.get(id);
			if(ss.getAttribute("appModel") != null){
				userList.add((ApplicationModel) logginedSessions.get(id).getAttribute("appModel"));
			}
		}
	}
	public GridBean getOnlineUserListForGrid(GridBean grid){
		grid.setRecords(this.getOnlineUserCount());
		int from = grid.getStart();
		int end = grid.getStart()+grid.getPageCount();
		if(end > grid.getRecords()){
			end = grid.getRecords();
		}
		if(from > end){
			from = end;
		}
		if(from < 0){
			from = 0;
		}
		if(end < 0){
			end = 0;
		}
		grid.setRows(userList.subList(from, end).toArray());
		return grid;
	}
}
