package prompt.yamba;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public final class TimelineProvider extends ContentProvider {

	private static final int STATUS_ITEM_LIST = 0;
	private static final int STATUS_ITEM_ID = 1;
	
	private static final UriMatcher _UriMatcher;
	private static final String[] _MimeTypes;
	
	static {
		_UriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		_UriMatcher.addURI(TimelineProviderContract.AUTHORITY, "/",
				STATUS_ITEM_LIST); // /user
		_UriMatcher.addURI(TimelineProviderContract.AUTHORITY, "#",
				STATUS_ITEM_ID); // /user#
		_MimeTypes = new String[]{
				TimelineProviderContract.TIMELINE_LIST_TYPE,
				TimelineProviderContract.TIMELINE_ITEM_TYPE,
		};
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		int matchCode = _UriMatcher.match(uri);
		if(matchCode == UriMatcher.NO_MATCH)
			return null;
		return _MimeTypes[matchCode];
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// projection = colunas a seleccionar , selection = where
		// selectionArgs = argumentos para where , sortOrder = order by
		// TODO Auto-generated method stub
		int matchCode = _UriMatcher.match(uri);
		if(matchCode == UriMatcher.NO_MATCH)
			return null;
		
		MatrixCursor cursor = null;
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
