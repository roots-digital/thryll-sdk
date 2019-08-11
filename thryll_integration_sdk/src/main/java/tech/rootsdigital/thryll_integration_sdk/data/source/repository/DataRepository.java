/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.rootsdigital.thryll_integration_sdk.data.source.repository;

import androidx.annotation.NonNull;


/**
 * Concrete implementation to load data from the data sources loadEventsInto a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class DataRepository implements DataRequests {

    private final String TAG = DataRepository.class.getSimpleName();
    private static DataRepository INSTANCE = null;

    private final RemoteDataRequests mRemoteDataSource;

    private final LocalDataRequests mLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    //TODO: Define cache variables

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private DataRepository(@NonNull RemoteDataRequests remoteDataSource,
                           @NonNull LocalDataRequests localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link DataRepository} instance
     */
    public static DataRepository getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new DataRepository(new RemoteDataRequests(), new LocalDataRequests());
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance()} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }



}
