//  ___            _  __      __                  
// | _ )  ___   __| | \ \    / /  __ _   _ _   ___
// | _ \ / -_) / _` |  \ \/\/ /  / _` | | '_| (_-<
// |___/ \___| \__,_|   \_/\_/   \__,_| |_|   /__/
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.bedwars;

public enum GameStatus {

	LOBBY, INGAME, RESTART_LOBBY, RESTART;

	public static GameStatus status;

	public GameStatus getStatus() {
		return status;
	}

	public static void setStatus(GameStatus gstatus) {
		status = gstatus;
	}

	public static boolean isStatus(GameStatus gstatus) {
		if (status == gstatus)
			return true;
		else
			return false;
	}

}
