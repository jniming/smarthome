package com.kqt.smarthome.db;

import java.util.ArrayList;
import java.util.List;

import com.kqt.smarthome.entity.AlarmMsg;
import com.kqt.smarthome.entity.Device;
import com.kqt.smarthome.entity.IpcDevice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DeviceManager {

	private DeviceDb db;
	private SQLiteDatabase Readdatabase, wirtedatabase;
	private static DeviceManager deviceManager;

	public static DeviceManager getInstence(Context context) {
		if (deviceManager == null) {
			deviceManager = new DeviceManager(context);

		}
		return deviceManager;

	}

	public DeviceManager(Context context) {
		db = new DeviceDb(context);
		Readdatabase = db.getReadableDatabase();
		wirtedatabase = db.getWritableDatabase();
	}

	public List<IpcDevice> GetIPCListDevice() {
		Cursor cursor = Readdatabase.query(DeviceDb.tableIPCName, new String[] {
				"device_mac", "device_adminname", "device_pwd", "device_name",
				"device_userid", "device_pic", "device_status" }, null, null,
				null, null, null);
		List<IpcDevice> list = new ArrayList<IpcDevice>();
		while (cursor.moveToNext()) {
			IpcDevice ipcDevice = new IpcDevice();
			String mac = cursor.getString(0);
			String adminName = cursor.getString(1);
			String pwd = cursor.getString(2);
			String name = cursor.getString(3);
			int device_userid = cursor.getInt(4);
			String pic = cursor.getString(5);
			int status = cursor.getInt(6);
			ipcDevice.setDeviceid(mac);
			ipcDevice.setName(name);
			ipcDevice.setAdmin_name(adminName);
			ipcDevice.setPsw(pwd);
			ipcDevice.setUserid(device_userid);
			ipcDevice.setPic(pic);
			ipcDevice.setConnect_state(status);
			list.add(ipcDevice);
		}
		return list;
	}

	/**
	 * 通过userid查找ipc设备
	 * 
	 * @param userid
	 * @return
	 */
	public IpcDevice QueryDevice(long userid) {
		Cursor cursor = Readdatabase.query(DeviceDb.tableIPCName, new String[] {
				"device_mac", "device_adminname", "device_pwd", "device_name",
				"device_pic" }, "device_userid=?",
				new String[] { userid + "" }, null, null, null);
		IpcDevice device = new IpcDevice();
		if (cursor.moveToFirst()) {
			String mac = cursor.getString(0);
			String adminName = cursor.getString(1);
			String pwd = cursor.getString(2);
			String name = cursor.getString(3);
			String pic = cursor.getString(4);
			device.setDeviceid(mac);
			device.setName(name);
			device.setAdmin_name(adminName);
			device.setPsw(pwd);
			device.setUserid(userid);
			device.setPic(pic);
			return device;
		}

		return null;

	}

	/**
	 * 插入图片
	 * 
	 * @param device
	 * @return
	 */
	public boolean updateIPC_name(IpcDevice device) {
		ContentValues values = new ContentValues();
		values.put("device_name", device.getName());
		long id = wirtedatabase.update(DeviceDb.tableIPCName, values,
				"device_userid=?", new String[] { device.getUserid() + "" });
		if (id > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 插入图片
	 * 
	 * @param device
	 * @return
	 */
	public boolean updateIPC_pic(IpcDevice device) {
		ContentValues values = new ContentValues();
		values.put("device_pic", device.getPic());
		long id = wirtedatabase.update(DeviceDb.tableIPCName, values,
				"device_userid=?", new String[] { device.getUserid() + "" });
		if (id > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 插入图片
	 * 
	 * @param device
	 * @return
	 */
	public boolean updateIPC_status(long userid, int status) {
		ContentValues values = new ContentValues();
		values.put("device_status", status);
		long id = wirtedatabase.update(DeviceDb.tableIPCName, values,
				"device_userid=?", new String[] { userid + "" });
		if (id > 0) {
			return true;
		}
		return false;
	}

	public boolean SaveIpcDevice(IpcDevice device) {
		ContentValues values = new ContentValues();
		values.put("device_name", device.getName());
		values.put("device_pwd", device.getPsw());
		values.put("device_mac", device.getDeviceid());
		values.put("device_userid", device.getUserid());
		values.put("device_pic", device.getPic());

		long id = wirtedatabase.insert(DeviceDb.tableIPCName, null, values);

		if (id > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 保存消息
	 * 
	 * @param am
	 * @return
	 */
	public boolean SaveMsg(AlarmMsg am) {
		ContentValues values = new ContentValues();
		values.put("time", am.getTime());
		values.put("device_mac", am.getMac());
		values.put("msg", am.getMsg());
		values.put("filepath", am.getFilepath());
		long id = wirtedatabase.insert(DeviceDb.tableMsg, null, values);
		if (id > 0) {
			return true;

		}

		return false;
	}

	/**
	 * 查询消息
	 * 
	 * @param mac
	 * @return
	 */
	public List<AlarmMsg> QueryMsg(String mac) {
		List<AlarmMsg> alarmMsgs = new ArrayList<AlarmMsg>();
		Cursor cursor = Readdatabase.query(DeviceDb.tableMsg, new String[] {
				"msg_id", "device_mac", "time", "msg", "filepath" },
				"device_mac=?", new String[] { mac }, null, null, null);
		while (cursor.moveToNext()) {
			AlarmMsg alarm = new AlarmMsg();

			int id = cursor.getInt(0);
			String mac1 = cursor.getString(1);
			String time = cursor.getString(2);
			String msg = cursor.getString(3);
			String file = cursor.getString(4);
			alarm.setId(id);
			alarm.setMac(mac1);
			alarm.setTime(time);
			alarm.setMsg(msg);
			alarm.setFilepath(file);
			alarmMsgs.add(alarm);
		}
		return alarmMsgs;
	}

	// /**
	// * 获取所有设备列表
	// *
	// * @return
	// */
	// public List<Device> GetListALLDevice() {
	// List<Device> list = new ArrayList<Device>();
	//
	// Cursor cursor = Readdatabase.query(DeviceDb.tableName, new String[] {
	// "deviceUUid", "device_name", "device_type", "deviceuserid",
	// "status", "deviceid" }, null, null, null, null, null);
	//
	// while (cursor.moveToNext()) {
	// int dType = cursor.getInt(2);
	// String dName = cursor.getString(1);
	// String uuid = cursor.getString(0);
	// int userid = cursor.getInt(3);
	// int status = cursor.getInt(4);
	// int deviceid = cursor.getInt(5);
	// Device device = new Device(dType, dName, uuid, userid, status,
	// deviceid);
	// list.add(device);
	// }
	// if (list.size() == 0) {
	// return null;
	// }
	// Readdatabase.close();
	// return list;
	//
	// }

	public int delectDevice(String uuid) {

		int index = wirtedatabase.delete(DeviceDb.tableIPCName, "device_mac=?",
				new String[] { uuid });
		return index;
	}

	public long SaveDevice(Device device) {
		long id = 0;

		Log.d("zjm", device.getUuid());
		Cursor cursor = Readdatabase.rawQuery("select * from "
				+ DeviceDb.tableName + " where deviceUUid=?",
				new String[] { device.getUuid() });

		if (!cursor.moveToNext()) {
			ContentValues values = new ContentValues();
			values.put("deviceUUid", device.getUuid());
			values.put("device_name", device.getdName());
			values.put("device_type", device.getdType());
			values.put("deviceuserid", device.getUserid());
			values.put("deviceid", device.getDeviceid());

			id = wirtedatabase.insert(DeviceDb.tableName, null, values);
		}
		cursor.close();
		return id;

	}
}
