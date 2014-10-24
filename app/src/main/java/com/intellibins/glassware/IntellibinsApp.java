/*
 * *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.intellibins.glassware;

import com.intellibins.glassware.binlocation.BinLocationModule;
import com.intellibins.glassware.dropofflocation.DropOffLocationModule;
import com.intellibins.glassware.storelocation.PlaceApiModule;
import com.intellibins.glassware.userlocation.UserLocationModule;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by prt2121 on 9/27/14.
 */
public class IntellibinsApp extends Application {

    private static final String TAG = IntellibinsApp.class.getSimpleName();

    private ObjectGraph graph;

    public static IntellibinsApp get(Context context) {
        return (IntellibinsApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
    }

    private void buildObjectGraphAndInject() {
        graph = ObjectGraph.create(new IntellibinsModule(this),
                new BinLocationModule(this),
                new PlaceApiModule(),
                new DropOffLocationModule(),
                new UserLocationModule(this));
    }

    public void inject(Object o) {
        graph.inject(o);
    }
}
