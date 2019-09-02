import * as React from 'react';
import CatsProposalComponent from '../components/CatsProposal';
import { CatsProposal } from '../model/CatsProposal';
import { ICatsProposalUI } from '../model/ICatsProposalUI';

// tslint:disable-next-line:no-empty-interface
interface CatsProposalProps {
}

interface CatsProposalState {
  catsProposal: CatsProposal;
  selectedCat: string;
}

class CatsProposalUI extends React.Component<CatsProposalProps, CatsProposalState> implements ICatsProposalUI {

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
    return <CatsProposalComponent catsProposal={catsProposal} catsProposalUI={this}/>;
  }

  public selectCat(catsProposal: CatsProposal, selectedCatId: string): void {
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

  private populateWithNewCat() {
    // tslint:disable-next-line:no-console
    console.log(`populateWithNewCat`);
    fetch('api/cats/proposal', {method: 'POST'})
      .then(response => response.json())
      .then(data => this.setState({catsProposal: data}));
  }
}

export default CatsProposalUI;
