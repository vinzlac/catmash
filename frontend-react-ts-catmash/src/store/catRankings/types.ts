import { CatRanking } from '../../model/CatRanking';

export interface CatRankingsState {
    catRankings: CatRanking [];
    error ?: Error;
    pending: boolean;
}

export const FETCH_CAT_RANKINGS_PENDING = 'FETCH_CAT_RANKINGS_PENDING';
export const FETCH_CAT_RANKINGS_SUCCESS = 'FETCH_CAT_RANKINGS_SUCCESS';
export const FETCH_CAT_RANKINGS_ERROR = 'FETCH_CAT_RANKINGS_ERROR';

export interface FetchCatRankingsPendingAction {
    type: typeof FETCH_CAT_RANKINGS_PENDING;
}

export interface FetchCatRankingsSuccessAction {
    type: typeof FETCH_CAT_RANKINGS_SUCCESS;
    catRankings: CatRanking [];
}

export interface FetchCatRankingsErrorAction {
    type: typeof FETCH_CAT_RANKINGS_ERROR;
    error: Error;
}

export type FetchCatRankingsActionTypes = FetchCatRankingsPendingAction | FetchCatRankingsSuccessAction
| FetchCatRankingsErrorAction;
