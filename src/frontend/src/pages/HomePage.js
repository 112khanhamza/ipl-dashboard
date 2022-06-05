import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { TeamTile } from '../components/TeamTile';
import './HomePage.scss';

export const HomePage = () => {

    const [teams, setTeam] = useState([]);
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchAllTeam = async () => {
                const response = await fetch(`http://localhost:8080/team`);
                const data = await response.json();
                setTeam(data);
            }
            fetchAllTeam();
        }, []
    );

    return (
        <div className="HomePage">
        <div className='header-section'>
            <h1 className='app-name'>Java Brains IPL Dashboard</h1>
        </div>
        <div className='team-grid'>
            {
                teams.map(team => <TeamTile teamName={team.teamName} />)
            }
        </div>
        </div>
    );
}
