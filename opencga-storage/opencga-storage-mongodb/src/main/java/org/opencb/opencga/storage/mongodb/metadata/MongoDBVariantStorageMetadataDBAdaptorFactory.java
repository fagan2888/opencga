package org.opencb.opencga.storage.mongodb.metadata;

import org.opencb.commons.datastore.core.ObjectMap;
import org.opencb.commons.datastore.mongodb.MongoDataStore;
import org.opencb.opencga.storage.core.metadata.adaptors.ProjectMetadataAdaptor;
import org.opencb.opencga.storage.core.metadata.adaptors.StudyConfigurationAdaptor;
import org.opencb.opencga.storage.core.metadata.adaptors.VariantFileMetadataDBAdaptor;
import org.opencb.opencga.storage.core.metadata.adaptors.VariantStorageMetadataDBAdaptorFactory;
import org.opencb.opencga.storage.mongodb.variant.MongoDBVariantStorageEngine;

/**
 * Created on 02/05/18.
 *
 * @author Jacobo Coll &lt;jacobo167@gmail.com&gt;
 */
public class MongoDBVariantStorageMetadataDBAdaptorFactory implements VariantStorageMetadataDBAdaptorFactory {

    private final MongoDataStore db;
    private final ObjectMap options;

    public MongoDBVariantStorageMetadataDBAdaptorFactory(MongoDataStore db, ObjectMap options) {
        this.db = db;
        this.options = options;
    }

    @Override
    public ProjectMetadataAdaptor buildProjectMetadataDBAdaptor() {
        return new MongoDBProjectMetadataDBAdaptor(db, options.getString(
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_PROJECT.key(),
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_PROJECT.defaultValue()
                ));
    }

    @Override
    public StudyConfigurationAdaptor buildStudyConfigurationDBAdaptor() {
        return new MongoDBStudyConfigurationDBAdaptor(db, options.getString(
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_STUDIES.key(),
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_STUDIES.defaultValue()
                ));
    }

    @Override
    public VariantFileMetadataDBAdaptor buildVariantFileMetadataDBAdaptor() {
        return new MongoDBVariantFileMetadataDBAdaptor(db, options.getString(
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_FILES.key(),
                MongoDBVariantStorageEngine.MongoDBVariantOptions.COLLECTION_FILES.defaultValue()
        ));
    }
}
