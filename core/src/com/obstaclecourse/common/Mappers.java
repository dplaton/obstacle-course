package com.obstaclecourse.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.PickupComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.ObstacleComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.TextureComponent;

/**
 * Created by platon on 21/07/2017.
 */

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS_MAPPER = ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT_MAPPER = ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION_MAPPER = ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<ObstacleComponent> OBSTACLE_MAPPER = ComponentMapper.getFor(ObstacleComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE_MAPPER = ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION_MAPPER = ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<PickupComponent> PICKUP_MAPPER = ComponentMapper.getFor(PickupComponent.class);
}
