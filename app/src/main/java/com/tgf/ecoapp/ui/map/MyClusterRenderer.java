package com.tgf.ecoapp.ui.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by Martin B. on 8/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {
    public MyClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        // Personalizar la apariencia del marcador individual (si deseas)
        // Por ejemplo:
        // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
        // Personalizar la apariencia del cluster (si deseas)
        // Por ejemplo:
        // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        super.onBeforeClusterRendered(cluster, markerOptions);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        // Especificar cuándo se deben agrupar los marcadores
        // Por ejemplo:
        // return cluster.getSize() > 1;
        return super.shouldRenderAsCluster(cluster);
    }
}

