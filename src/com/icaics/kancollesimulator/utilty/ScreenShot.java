package com.icaics.kancollesimulator.utilty;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.icaics.kancollesimulator.R;

public class ScreenShot {

	// ��ȡָ��Activity�Ľ��������浽png�ļ�

	@SuppressWarnings("deprecation")
	static Bitmap takeScreenShot(Activity activity, int type, int countShip) {

		// View������Ҫ��ͼ��View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bitmap = null;
		Bitmap bitmapTableLayout = null;
		Bitmap bitmapScrollView = null;
		Bitmap b = view.getDrawingCache();

		// ��ȡ״̬���߶�
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		System.out.println("statusBarHeight = " + statusBarHeight);

		// ��ȡ��Ļ�߶ȿ��
		int height = activity.getWindowManager().getDefaultDisplay().getHeight();
		System.out.println("height = " + height);
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();

		// ���ڲ��������������ֵĸ߶ȣ����ݸ߶ȣ�
		int contentHeight = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getHeight();
		System.out.println("contentHeight = " + contentHeight);

		// �������߶� = ��Ļ�߶� - ���ݸ߶� - ״̬���߶�
		int titleBarHeight = height - contentHeight - statusBarHeight;
		System.out.println("titleBarHeight = " + titleBarHeight);

		switch (type) {
		case 1:
			// �������
			View v = activity.findViewById(R.id.tableRowTop);
			int vHeight = v.getTop();
			System.out.println("vHeight = " + vHeight);
			// ����ͼƬ
			bitmap = Bitmap.createBitmap(b, 0, statusBarHeight, width, vHeight + titleBarHeight);
			break;
		case 2:
			// ��������
			ScrollView scrollView = (ScrollView) activity.findViewById(R.id.scrollView);
			TableLayout infoTableLayout = (TableLayout) activity.findViewById(R.id.tableRowEnd);
			// ��ȡTableLayout�߶�
			int heightInfoTableLayout = infoTableLayout.getHeight();
			// ����TableLayout
			bitmapTableLayout = Bitmap.createBitmap(b, 0, statusBarHeight, width, titleBarHeight + heightInfoTableLayout);
			// ��ȡScrollView�߶�
			int heightScrollView = 0;
			TableLayout tableLayoutInScrollView = (TableLayout) activity.findViewById(R.id.tableLayout);
			tableLayoutInScrollView.setBackgroundColor(Color.parseColor("#000000"));
			switch (countShip) {
			case 1:
				heightScrollView = ((TableRow) activity.findViewById(R.id.tableRow2)).getTop();
				break;
			case 2:
				heightScrollView = ((TableRow) activity.findViewById(R.id.tableRow3)).getTop();
				break;
			case 3:
				heightScrollView = ((TableRow) activity.findViewById(R.id.tableRow4)).getTop();
				break;
			case 4:
				heightScrollView = ((TableRow) activity.findViewById(R.id.tableRow5)).getTop();
				break;
			case 5:
				heightScrollView = ((TableRow) activity.findViewById(R.id.tableRow6)).getTop();
				break;
			case 6:
				heightScrollView = tableLayoutInScrollView.getHeight();
				break;
			default:
				break;
			}

			System.out.println("�������߶� = " + heightScrollView);

			// ����ScrollView
			bitmapScrollView = Bitmap.createBitmap(scrollView.getWidth(), heightScrollView, Bitmap.Config.ARGB_8888);
			// ScrollView��ͼ
			Canvas canvasScrollView = new Canvas(bitmapScrollView);
			scrollView.draw(canvasScrollView);
			// �ϳ�
			bitmap = Bitmap.createBitmap(width, titleBarHeight + heightInfoTableLayout + heightScrollView + 30, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			canvas.drawBitmap(bitmapTableLayout, 0, 0, paint);
			canvas.drawBitmap(bitmapScrollView, 0, titleBarHeight + heightInfoTableLayout, paint);
			canvas.drawRect(0, titleBarHeight + heightInfoTableLayout + heightScrollView, scrollView.getWidth(), titleBarHeight + heightInfoTableLayout + heightScrollView + 30, paint);
			// �������
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(ToolFunction.FORMATIONSHARE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				if (null != out) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
			break;
		default:
			break;
		}
		view.destroyDrawingCache();
		return bitmap;
	}

	// ���浽sdcard
	private static void savePic(Bitmap b, String strFileName) {

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(strFileName);
			System.out.println("strPicFileName = " + strFileName);
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void shoot(Activity a, String b, int type, int countShip) {
		Bitmap bitmap = ScreenShot.takeScreenShot(a, type, countShip);
		if (type == 1) {
			// �����������ʹ�� b ����ָ��·������ɷ���ͼƬ����λ����takeScreenShot��ָ��
			ScreenShot.savePic(bitmap, b);
		}
	}

}
