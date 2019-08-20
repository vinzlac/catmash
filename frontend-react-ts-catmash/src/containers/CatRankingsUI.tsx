import * as React from 'react';
import './CatRankingsUI.scss';
interface CatRanking {
  id: string;
  url: string;
  voteCount: number;
}

// tslint:disable-next-line:no-empty-interface
interface CatRankingProps {
}

interface CatRankingState {
  catRankings: CatRanking [];
}

class CatRankingsUI extends React.Component<CatRankingProps, CatRankingState> {
  constructor(props: CatRankingProps) {
    super(props);

    this.state = {
      catRankings: [],
    };
  }

  public componentDidMount() {
    this.populateWithCatsRanking();
  }

  public render() {
    const {catRankings} = this.state;

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

  private populateWithCatsRanking() {
    // tslint:disable-next-line:no-console
    console.log(`populateWithCatsRanking`);
    fetch('api/cats/ranking')
      .then(response => response.json())
      .then(data => this.setState({catRankings: data}));
  }
}

export default CatRankingsUI;
