import { ThunkAction } from 'redux-thunk';
import { AnyAction } from 'redux';

import { CatRanking } from '../../model/CatRanking';
import * as types from './types';

export function fetchCatRankingsPending(): types.FetchCatRankingsPendingAction {
    return {
      type: types.FETCH_CAT_RANKINGS_PENDING,
    };
}

export function fetchCatRankingsSuccess(catRankings: CatRanking []): types.FetchCatRankingsSuccessAction {
    return {
        catRankings,
        type: types.FETCH_CAT_RANKINGS_SUCCESS,
    };
}

export function fetchCatRankingsError(error: Error): types.FetchCatRankingsErrorAction {
    return {
        error,
        type: types.FETCH_CAT_RANKINGS_ERROR,
    };
}

export function fetchCatRankings(): ThunkAction<Promise<void>, {}, {}, AnyAction> {
    return async (dispatch: (action: types.FetchCatRankingsActionTypes) => void) => {
        dispatch(fetchCatRankingsPending());
        try {
            const res = await fetch('api/cats/ranking');
            const json = await res.json();
            dispatch(fetchCatRankingsSuccess(json));
        } catch (error) {
            dispatch(fetchCatRankingsError(error));
        }
    };
}
