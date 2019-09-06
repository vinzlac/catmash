import * as types from './types';

const initialState: types.CatRankingsState = {
    catRankings: [],
    error: undefined,
    pending: false,
};

export default function catRankingsReducer(
  state: types.CatRankingsState = initialState,
  action: types.FetchCatRankingsActionTypes
): types.CatRankingsState {
  // tslint:disable-next-line:no-console
  console.log('catRankingsReducer', state, action);
  switch (action.type) {
    case types.FETCH_CAT_RANKINGS_PENDING:
      return {
        ...state,
        pending: true,
      };
    case types.FETCH_CAT_RANKINGS_SUCCESS:
      return {
        ...state,
        catRankings:  action.catRankings,
        pending: false,
      };
    case types.FETCH_CAT_RANKINGS_ERROR:
      return {
        ...state,
        error:  action.error,
        pending: false,
      };
    default:
      return state;
  }
}
