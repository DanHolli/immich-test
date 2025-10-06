package app.alextran.immich

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.File

class MediaContentProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "app.alextran.immich"
        const val MEDIA_TABLE = "media"

        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$MEDIA_TABLE")

        private const val MEDIA = 1
        private const val MEDIA_ID = 2

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, MEDIA_TABLE, MEDIA)
            addURI(AUTHORITY, "$MEDIA_TABLE/#", MEDIA_ID)
        }
    }

    override fun onCreate(): Boolean {
        // Initialize your backend connection or local database here if needed
        return true
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?
    ): Cursor? {
        // TODO: Query Immich's local DB or cache for media info and return a Cursor
        return null
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            MEDIA -> "vnd.android.cursor.dir/$MEDIA_TABLE"
            MEDIA_ID -> "vnd.android.cursor.item/$MEDIA_TABLE"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
        // TODO: Return a file descriptor for the requested photo or video
        return null
    }
}
