import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IArtikal, defaultValue } from 'app/shared/model/artikal.model';

export const ACTION_TYPES = {
  FETCH_ARTIKAL_LIST: 'artikal/FETCH_ARTIKAL_LIST',
  FETCH_ARTIKAL: 'artikal/FETCH_ARTIKAL',
  CREATE_ARTIKAL: 'artikal/CREATE_ARTIKAL',
  UPDATE_ARTIKAL: 'artikal/UPDATE_ARTIKAL',
  DELETE_ARTIKAL: 'artikal/DELETE_ARTIKAL',
  RESET: 'artikal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IArtikal>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ArtikalState = Readonly<typeof initialState>;

// Reducer

export default (state: ArtikalState = initialState, action): ArtikalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ARTIKAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ARTIKAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ARTIKAL):
    case REQUEST(ACTION_TYPES.UPDATE_ARTIKAL):
    case REQUEST(ACTION_TYPES.DELETE_ARTIKAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ARTIKAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ARTIKAL):
    case FAILURE(ACTION_TYPES.CREATE_ARTIKAL):
    case FAILURE(ACTION_TYPES.UPDATE_ARTIKAL):
    case FAILURE(ACTION_TYPES.DELETE_ARTIKAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARTIKAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARTIKAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ARTIKAL):
    case SUCCESS(ACTION_TYPES.UPDATE_ARTIKAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ARTIKAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/artikals';

// Actions

export const getEntities: ICrudGetAllAction<IArtikal> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ARTIKAL_LIST,
  payload: axios.get<IArtikal>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IArtikal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ARTIKAL,
    payload: axios.get<IArtikal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IArtikal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ARTIKAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IArtikal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ARTIKAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IArtikal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ARTIKAL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
