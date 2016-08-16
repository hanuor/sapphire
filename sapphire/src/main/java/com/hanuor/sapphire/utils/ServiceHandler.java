package com.hanuor.sapphire.utils;


import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;

/**
 * Created by Shantanu Johri on 16-08-2016.
 */
public class ServiceHandler {

    public static final StorageService storageService = App42API.buildStorageService();
}
