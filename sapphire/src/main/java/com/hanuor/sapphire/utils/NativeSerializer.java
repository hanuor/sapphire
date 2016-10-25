package com.hanuor.sapphire.utils;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class NativeSerializer implements Serializable {

    private String tag;
    private Intent intent;

    public NativeSerializer(
            final String newTag,
            final Intent newIntent)
    {
        this.tag = newTag;
        this.intent = newIntent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public String toString()
    {
        return this.tag + " of " + this.intent;
    }

   /* private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.writeUTF(this.tag);
        out.writeUTF(this.intent.hasExtra());
        out.writeUTF(this.cityAndState.getStateName());
    }

    *//**
     * Deserialize this instance from input stream.
     *
     * @param in Input Stream from which this instance is to be deserialized.
     * @throws IOException Thrown if error occurs in deserialization.
     * @throws ClassNotFoundException Thrown if expected class is not found.
     *//*
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        this.lastName = in.readUTF();
        this.firstName = in.readUTF();
        this.cityAndState = new CityState(in.readUTF(), in.readUTF());
    }
*/
    private void readObjectNoData() throws ObjectStreamException
    {
        throw new InvalidObjectException("Stream data required");
    }
}
