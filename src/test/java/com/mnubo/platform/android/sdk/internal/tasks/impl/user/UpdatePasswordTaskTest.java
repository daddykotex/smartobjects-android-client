/*
 * Copyright (c) 2015 Mnubo. Released under MIT License.
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in
 *     all copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *     THE SOFTWARE.
 */

package com.mnubo.platform.android.sdk.internal.tasks.impl.user;

import com.mnubo.platform.android.sdk.internal.tasks.impl.AbstractTaskTest;
import com.mnubo.platform.android.sdk.models.security.UpdatePassword;

import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class UpdatePasswordTaskTest extends AbstractTaskTest {

    @Test
    public void testExecuteMnuboCall() throws Exception {
        final String username = "username";
        final UpdatePassword updatePassword = new UpdatePassword("password", "new_password", "confirm");
        UpdatePasswordTask updatePasswordTask = new UpdatePasswordTask(connection, username, updatePassword);

        updatePasswordTask.executeMnuboCall();

        verify(userService).updatePassword(eq(username), eq(updatePassword));
    }
}