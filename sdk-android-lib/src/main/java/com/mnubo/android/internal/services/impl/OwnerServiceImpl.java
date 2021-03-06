/*
 * Copyright (c) 2017 Mnubo. Released under MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mnubo.android.internal.services.impl;

import com.mnubo.android.exceptions.MnuboException;
import com.mnubo.android.internal.connect.MnuboConnectionManager;
import com.mnubo.android.internal.services.OwnerService;
import com.mnubo.android.models.Owner;
import com.mnubo.android.models.SmartObject;

import lombok.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Request;

import static com.mnubo.android.models.Owner.OWNERS_PATH;
import static com.mnubo.android.utils.HttpUtils.addPathVariables;
import static com.mnubo.android.utils.HttpUtils.buildBody;
import static com.mnubo.android.utils.HttpUtils.executeAndThrowOnFailure;


public class OwnerServiceImpl extends AbstractMnuboService implements OwnerService {

    public OwnerServiceImpl(MnuboConnectionManager connectionManager, HttpUrl platformBaseUrl) {
        super(connectionManager, platformBaseUrl, OWNERS_PATH);
    }

    @Override
    public void delete() throws MnuboException {
        Request request =
                requestBuilder()
                        .url(addPathVariables(getUrl(), getUsername()))
                        .delete()
                        .build();
        executeAndThrowOnFailure(getOkHttpClient(), request);
    }

    @Override
    public void update(@NonNull Owner owner) throws MnuboException {
        Request request =
                requestBuilder()
                        .url(addPathVariables(getUrl(), getUsername()))
                        .put(buildBody(owner))
                        .build();
        executeAndThrowOnFailure(getOkHttpClient(), request);
    }

    @Override
    public void create(String password, Owner owner) throws MnuboException {
        final Owner toCreate =
                Owner.builder()
                .attributes(owner.getAttributes())
                .attribute("username", getUsername())
                .attribute("x_password", password)
                .build();

        Request request =
                requestBuilder()
                        .url(addPathVariables(getUrl()))
                        .post(buildBody(toCreate))
                        .build();
        executeAndThrowOnFailure(getOkHttpClient(), request);
    }

    @Override
    public void createObject(@NonNull String deviceId, @NonNull String objectType, @NonNull SmartObject smartObject) throws MnuboException {
        final SmartObject toCreate =
                SmartObject.builder()
                        .attributes(smartObject.getAttributes())
                        .attribute("x_device_id", deviceId)
                        .attribute("x_object_type", objectType)
                        .build();

        Request request =
                requestBuilder()
                        .url(addPathVariables(getUrl(), getUsername(), SmartObject.OBJECTS_PATH))
                        .post(buildBody(toCreate))
                        .build();
        executeAndThrowOnFailure(getOkHttpClient(), request);
    }

    @Override
    public void deleteObject(@NonNull String deviceId) throws MnuboException {
        Request request =
                requestBuilder()
                        .url(addPathVariables(getUrl(), getUsername(), SmartObject.OBJECTS_PATH, deviceId))
                        .delete()
                        .build();
        executeAndThrowOnFailure(getOkHttpClient(), request);
    }
}
