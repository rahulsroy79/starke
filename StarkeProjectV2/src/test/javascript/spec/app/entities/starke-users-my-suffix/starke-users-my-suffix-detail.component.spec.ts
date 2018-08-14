/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUsersMySuffixDetailComponent } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix-detail.component';
import { StarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUsersMySuffix Management Detail Component', () => {
        let comp: StarkeUsersMySuffixDetailComponent;
        let fixture: ComponentFixture<StarkeUsersMySuffixDetailComponent>;
        const route = ({ data: of({ starkeUsers: new StarkeUsersMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUsersMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StarkeUsersMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeUsersMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.starkeUsers).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
