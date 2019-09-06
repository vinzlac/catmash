import * as React from 'react';
import { connect } from 'react-redux';
import './CatRankingsUI.scss';
import { CatRanking } from '../model/CatRanking';
import { ThunkDispatch } from 'redux-thunk';
import { fetchCatRankings } from '../store/catRankings/actions';
import { CatRankingsState } from '../store/catRankings/types';

// tslint:disable-next-line:no-empty-interface
interface OwnProps {
}

interface CatRankingProps {
  catRankings: CatRanking [];
  error?: Error;
  pending: boolean;
}

interface DispatchProps {
  fetchCatRankings: () => void;
}

type AllCatsRankingsProps = CatRankingProps & OwnProps & DispatchProps ;

class CatRankingsUI extends React.Component<AllCatsRankingsProps, CatRankingsState> {
  constructor(props: AllCatsRankingsProps) {
    super(props);
  }

  public componentDidMount() {
    this.props.fetchCatRankings();
  }

  public render() {
    const {catRankings, error, pending} = this.props;

    if (error) {
      return <div>Error! {error.message}</div>;
    }

    if (pending) {
      return <div>Loading...</div>;
    }

    return (
      <div>
          <h2>Cats Ranking</h2>
          <div className="cat-rankings__catList">
          {catRankings.map((catRanking: CatRanking) =>
            <div key={catRanking.id} className="cat-rankings__catWithVote">
              <img src={catRanking.url} alt={catRanking.url} width="400"/>
              <span>nb Votes : {catRanking.voteCount}</span>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: CatRankingsState, ownProps: OwnProps): CatRankingProps => ({
  catRankings: state.catRankings,
  error : state.error,
  pending: state.pending,
});

const mapDispatchToProps = (dispatch: ThunkDispatch<{}, {}, any>, ownProps: OwnProps): DispatchProps => {
  return {
    fetchCatRankings: async () => {
      await dispatch(fetchCatRankings());
      // tslint:disable-next-line:no-console
      console.log('FetchCatRankings completed [UI]');
    },
  };
};

// export default CatRankingsUI;
export default connect(mapStateToProps, mapDispatchToProps)(CatRankingsUI);
