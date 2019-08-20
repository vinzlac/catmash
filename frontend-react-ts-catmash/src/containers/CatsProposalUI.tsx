import * as React from 'react';
import './CatsProposalUI.css';

interface CatsProposal {
  uuid: string;
  cats: Cat[];
}

interface Cat {
  id: string;
  url: string;
}

// tslint:disable-next-line:no-empty-interface
interface CatsProposalProps {
}

interface CatsProposalState {
  catsProposal: CatsProposal;
  selectedCat: string;
}

class CatsProposalUI extends React.Component<CatsProposalProps, CatsProposalState> {

  constructor(props: CatsProposalProps) {
    super(props);

    this.state = {
      catsProposal: {uuid: '', cats: []},
      selectedCat: '',
    };
  }

  public componentDidMount() {
    this.populateWithNewCat();
  }

  public render() {
    const {catsProposal} = this.state;

    // tslint:disable-next-line:no-console
    console.log(`uuid= ${catsProposal.uuid}`);
    return (
      <div>
        <h2>Click on your prefered cat</h2>
        <div key={catsProposal.uuid} className="cats-proposal__choice">
          {catsProposal.cats.map((cat: Cat) =>
            <div key={cat.id}>
              <img src={cat.url} alt={cat.url} width="400" onClick={() => this.selectCat(catsProposal, cat.id)}/>
            </div>
          )}
        </div>
      </div>
    );
  }

  private populateWithNewCat() {
    // tslint:disable-next-line:no-console
    console.log(`populateWithNewCat`);
    fetch('api/cats/proposal', {method: 'POST'})
      .then(response => response.json())
      .then(data => this.setState({catsProposal: data}));
  }

  private selectCat(catsProposal: CatsProposal, selectedCatId: string) {
    fetch(`api/cats/${selectedCatId}/vote`,
        {
          body: JSON.stringify(catsProposal),
          headers: {
            'Content-Type': 'application/json',
          },
          method: 'POST',
        }
      )
      .then(response => this.populateWithNewCat());
  }

}

export default CatsProposalUI;
